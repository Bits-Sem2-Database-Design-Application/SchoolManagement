package bits.pilani.sem2.dda.assignment1.controller;

import bits.pilani.sem2.dda.assignment1.dto.StudentApplicationDTO;
import bits.pilani.sem2.dda.assignment1.entity.Address;
import bits.pilani.sem2.dda.assignment1.entity.Contact;
import bits.pilani.sem2.dda.assignment1.entity.Program;
import bits.pilani.sem2.dda.assignment1.entity.StudentAddress;
import bits.pilani.sem2.dda.assignment1.entity.StudentAddressPK;
import bits.pilani.sem2.dda.assignment1.entity.StudentApplication;
import bits.pilani.sem2.dda.assignment1.entity.StudentContact;
import bits.pilani.sem2.dda.assignment1.entity.StudentContactPK;
import bits.pilani.sem2.dda.assignment1.repository.AddressRepository;
import bits.pilani.sem2.dda.assignment1.repository.ContactRepository;
import bits.pilani.sem2.dda.assignment1.repository.ProgramRepository;
import bits.pilani.sem2.dda.assignment1.repository.StudentAddressRepository;
import bits.pilani.sem2.dda.assignment1.repository.StudentApplicationRepository;
import bits.pilani.sem2.dda.assignment1.repository.StudentContactRepository;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import static bits.pilani.sem2.dda.assignment1.Constants.*;
import static bits.pilani.sem2.dda.assignment1.controller.ControllerHelper.*;
import bits.pilani.sem2.dda.assignment1.dto.AddressDTO;
import bits.pilani.sem2.dda.assignment1.dto.ContactDTO;
import bits.pilani.sem2.dda.assignment1.dto.FeesTransactionDTO;
import bits.pilani.sem2.dda.assignment1.entity.ChangeRequest;
import bits.pilani.sem2.dda.assignment1.entity.Fees;
import bits.pilani.sem2.dda.assignment1.entity.FeesType;
import bits.pilani.sem2.dda.assignment1.entity.Student;
import bits.pilani.sem2.dda.assignment1.entity.StudentInfo;
import bits.pilani.sem2.dda.assignment1.repository.ChangeRequestRepository;
import bits.pilani.sem2.dda.assignment1.repository.FeesRepository;
import bits.pilani.sem2.dda.assignment1.repository.FeesTypeRepository;
import bits.pilani.sem2.dda.assignment1.repository.StudentInfoRepository;
import bits.pilani.sem2.dda.assignment1.repository.StudentRepository;

/**
 *
 * @author yogeshk
 */
@Controller
public class StudentApplicationController {

    private static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private ChangeRequestController changeRequestController;

    @Autowired
    private StudentApplicationRepository studentApplicationRepo;

    @Autowired
    private ProgramRepository programRepo;

    @Autowired
    private AddressRepository addressRepo;

    @Autowired
    private StudentAddressRepository studentAddressRepo;

    @Autowired
    private ContactRepository contactRepo;

    @Autowired
    private StudentContactRepository studentContactRepo;

    @Autowired
    private FeesTypeRepository feesTypeRepo;

    @Autowired
    private FeesRepository feesRepo;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private ChangeRequestRepository changeRequestRepo;
    
    @Autowired
    private StudentInfoRepository studentInfoRepo;
    
    /**
     * Step 1: Prospective student opens application form
     */
    @GetMapping("/student-application/apply")
    public String apply(Model model) {
        List<Program> programList = programRepo.findAll();
        model.addAttribute("isApplyPage", Boolean.TRUE); //for activating menu
        model.addAttribute("programList", programList);
        model.addAttribute("isStudentApplicationPage", Boolean.TRUE);
        return "apply";
    }

    /**
     * Step 2: Prospective student fills and submits application form
     */
    @PostMapping("/student-application/submit")
    @Transactional(rollbackFor = Exception.class)
    public String saveStudentApplication(StudentApplicationDTO dto,
        @RequestParam(name = "supportedDocsAsPDF") MultipartFile supportedDocsAsPDF,
        Model model) throws ParseException, IOException {

        StudentApplication application = new StudentApplication();
        application.setFirstName(dto.getFirstName());
        application.setLastName(dto.getLastName());
        application.setBirthdate(yyyyMMdd.parse(dto.getBirthDate()));
        application.setGender(dto.getGender());
        application.setEmail(dto.getEmail());
        application.setApplicationDate(new Date());
        application.setStatus(PENDING_REVIEW);
        application.setSupportingDocsAsSinglePdf(supportedDocsAsPDF.getBytes());

        Optional<Program> program = programRepo.findById(Long.parseLong(dto.getProgram()));
        if (program.isPresent()) {
            application.setProgramId(program.get());
        } else {
            throw new IllegalArgumentException("Invalid program id = " + dto.getProgram());
        }
        studentApplicationRepo.save(application);

        Address address = new Address();
        setAddressDetails(address, dto.getLine1(), dto.getLine2(), 
            dto.getCity(), dto.getState(), dto.getCountry()); //new Address();
        saveAddress(application, address, ADDRESS_PERMANENT, true);
        
        if (!dto.isTmpSameAsAbove()) {
            Address tmpAddress = new Address();
            setAddressDetails(tmpAddress, dto.getTmpLine1(), 
                dto.getTmpLine2(), dto.getTmpCity(), dto.getTmpState(), 
                dto.getTmpCountry());
            saveAddress(application, tmpAddress, ADDRESS_TEMPORARY, true);
        }
        Contact primary = new Contact();
        setContactDetails(primary, Integer.parseInt(dto.getCountryCode()), 
            Long.parseLong(dto.getPhoneNumber()), dto.getContactType());
        saveContact(application, primary, CONTACT_PRIMARY, true);
        
        Contact secondary = new Contact();
        setContactDetails(secondary, Integer.parseInt(dto.getAltCountryCode()), 
            Long.parseLong(dto.getAltPhoneNumber()), dto.getAltContactType());
        saveContact(application, secondary, CONTACT_SECONDARY, true);
        
        studentApplicationRepo.save(application);
        
        return "apply-success";
    }

    /**
     * Step 3: Admin lists all applications with status 'Pending Review'
     */
    @GetMapping("/admin/student-application/review")
    public String review(Model model) {
        List<StudentApplication> applicationList
            = studentApplicationRepo.findByStatus("Pending Review");
        model.addAttribute("applicationList", applicationList);
        return "admin/list-student-applications";
    }

    /**
     * Step 4: Admin open student application form for review
     */
    @GetMapping("/admin/student-application")
    public String showStudentApplication(@RequestParam Long applicationId,
        Model model) {
        Optional<StudentApplication> optional
            = studentApplicationRepo.findById(applicationId);
        StudentApplication application = optional.get();
        Pair<Optional<Contact>, Optional<Contact>> contacts = getPrimarySecondaryContact(
            application.getStudentContactList());
        Pair<Optional<Address>, Optional<Address>> addresses
            = getPermanentTemporaryAddresses(application.getStudentAddressList());

        model.addAttribute("app", application);
        if (contacts.getFirst().isPresent()) {
            Contact primary = contacts.getFirst().get();
            model.addAttribute("primaryContact", primary.getContactType() + ": "
                + primary.getCountryCode() + "-" + primary.getContactNumber());
        }
        if (contacts.getSecond().isPresent()) {
            Contact secondary = contacts.getFirst().get();
            model.addAttribute("secondaryContact", secondary.getContactType() + ": "
                + secondary.getCountryCode() + "-" + secondary.getContactNumber());
        }

        if (addresses.getFirst().isPresent()) {
            model.addAttribute("permanentAddress", addresses.getFirst().get());
        }
        if (addresses.getSecond().isPresent()) {
            model.addAttribute("temporaryAddress", addresses.getSecond().get());
        }

        if (FEES_PAID.equals(application.getStatus())) {
            List<Fees> feesList = feesRepo.findByStudentId(new Student(application.getId()));
            model.addAttribute("fees", feesList.get(0));
        }
        return "admin/view-student-application";
    }

    /**
     * Step 5: Admin opens PDF to review certificates and marksheets
     */
    @GetMapping("/admin/student-application/get-pdf")
    public void getSupportingDocsAsPDF(@RequestParam Long applicationId,
        HttpServletResponse response) throws IOException {
        Optional<StudentApplication> optional
            = studentApplicationRepo.findById(applicationId);
        StudentApplication application = optional.get();
        byte[] pdf = application.getSupportingDocsAsSinglePdf();
        String name = "SupportingDocsAsPdf_" + application.getFirstName() + "_"
            + application.getFirstName() + "_" + application.getId() + ".pdf";
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "inline; filename=" + name);
        response.setContentLength(pdf.length);
        response.getOutputStream().write(pdf);
        response.getOutputStream().flush();
    }

    /**
     * Step 6: Admin reviews student application. Update status with review
     * comments.
     */
    @Transactional
    @PostMapping("/admin/student-application/review-comments")
    public String reviewCommentStudentApplication(@RequestParam String comments,
        @RequestParam String status,
        @RequestParam Long applicationId, Model model) {

        if (comments == null || comments.trim().equals("")) {
            model.addAttribute("commentsError", "Comments Missing !!!");
            return showStudentApplication(applicationId, model);
        }

        StudentApplication application
            = studentApplicationRepo.findById(applicationId).get();
        application.setComments(comments);
        application.setStatus(status);
        model.addAttribute("successMessage", "'" + status
            + "' action taken! Email send to inform student.");
        return review(model);
    }

    /**
     * Step 7: Prospective student wants to check status of submitted
     * application. System presents form request email to retrieve form.
     */
    @GetMapping("/student-application/check-status/email")
    public String showCheckStatusForm(Model model) {
        model.addAttribute("email", "");
        model.addAttribute("emailReadonly", "false");
        model.addAttribute("otpDisplay", "none");
        model.addAttribute("formAction", "/student-application/check-status/otp");
        return "check-status-form";
    }

    /**
     * Step 8: System after validating email, generates and sends OTP. Student
     * is requested to enter OTP send on email.
     */
    @Transactional
    @PostMapping("/student-application/check-status/otp")
    public String showCheckStatusForm(@RequestParam String email, Model model) {
        if (email == null || email.trim().equals("")) {
            model.addAttribute("errorEmail", "Missing email!");
            return showCheckStatusForm(model);
        }
        Optional<StudentApplication> optional
            = studentApplicationRepo.findByEmail(email);
        if (optional.isEmpty()) {
            model.addAttribute("errorEmail", "No application found for specified email!");
            return showCheckStatusForm(model);
        }
        StudentApplication application = optional.get();

        //int otp = new Random().nextInt(100000, 999999);
        int otp = studentApplicationRepo.generateOtp(6);
        System.out.println("New OTP Created using stored procedure: " + otp);
        application.setOtp(otp);
        studentApplicationRepo.save(application);

        model.addAttribute("email", email);
        model.addAttribute("emailReadonly", "true");
        model.addAttribute("formAction", "/student-application/check-status");
        return "check-status-form";
    }

    /**
     * Step 9: System shows application status to student after entering valid
     * OTP as send in email.
     */
    @Transactional
    @PostMapping("/student-application/check-status")
    public String showStudentApplicationStatus(@RequestParam String email,
        @RequestParam String otp, Model model) {
        int intOtp = toIntOtp(otp);
        if (otp == null || otp.trim().equals("") || intOtp == -1) {
            model.addAttribute("errorOTP", "Missing or invalid OTP!");
            return showCheckStatusForm(email, model);
        }
        if (email == null || email.trim().equals("")) {
            model.addAttribute("errorEmail", "Missing email!");
            return showCheckStatusForm(email, model);
        }

        Optional<StudentApplication> optional
            = studentApplicationRepo.findByEmail(email);
        if (optional.isEmpty()) {
            model.addAttribute("errorEmail", "No application found for specified email!");
            return showCheckStatusForm(model);
        }
        StudentApplication application = optional.get();

        if (application.getOtp() != intOtp) {
            model.addAttribute("errorMessage", "OTP does not match!!!");
            return showCheckStatusForm(email, model);
        }

        application.setOtp(null);
        studentApplicationRepo.save(application);
        model.addAttribute("app", application);
        return "application-status";
    }

    /**
     * Step 10: Student uploaded additional documents against application status
     * 'Information Needed'.
     */
    @Transactional
    @PostMapping("/student-application/upload-information-needed")
    public String handleInformationNeeded(Long applicationId,
        @RequestParam(name = "supportedDocsAsPDF") MultipartFile supportedDocsAsPDF,
        Model model) throws IOException {

        StudentApplication application
            = studentApplicationRepo.findById(applicationId).get();
        application.setSupportingDocsAsSinglePdf(supportedDocsAsPDF.getBytes());
        application.setStatus(PENDING_REVIEW);

        model.addAttribute("successMessage", "Supporting files uploaded successfully.");
        return "index";
    }

    /**
     * Step 11: Student uploaded additional documents against application status
     * 'Approved'.
     */
    @Transactional
    @PostMapping("/student-application/submit-fee-details")
    public String handleApproved(FeesTransactionDTO dto, Model model)
        throws ParseException {
        StudentApplication application
            = studentApplicationRepo.findById(dto.getStudentId()).get();
        if (!valid(dto, model)) {
            model.addAttribute("app", application);
            return "application-status";
        }
        Student student = new Student();
        student.setId(dto.getStudentId());
        student.setEnrollmentDate(new Date());
        student.setRegistrationNumber("tmp-" + application.getId());
        student.setCurrentSemister(1);
        student.setProgramFees(application.getProgramId().getFees());

        studentRepo.save(student);

        FeesType feesType = feesTypeRepo.findById(1l).get();

        Fees fees = new Fees();
        fees.setStudentId(student);
        fees.setBank(dto.getBank());
        fees.setTransactionDate(yyyyMMdd.parse(dto.getTransactionDate()));
        fees.setMode(MODE_ONLINE_BANKING);
        fees.setReferenceNumber(dto.getTransactionNumber());
        fees.setTransactionAmount(dto.getTransactionAmount());
        fees.setSemester(1);
        fees.setFeesTypeId(feesType);

        feesRepo.save(fees);

        application.setStatus(FEES_PAID);
        studentApplicationRepo.save(application);

        model.addAttribute("successMessage", "Dear student, Administrator will review fees details and enroll you");
        model.addAttribute("successMessage1", "You will receive email on successful enrollment.");

        return "index";
    }

    @GetMapping("/admin/student-application/list-ready-to-enroll")
    public String listStudentsReadyToEnroll(Model model) {
        List<StudentApplication> applicationList
            = studentApplicationRepo.findByStatus(FEES_PAID);
        model.addAttribute("applicationList", applicationList);
        return "admin/list-student-applications";
    }

    @Transactional
    @PostMapping("/admin/student-application/enroll")
    public String enrollStudent(Long studentId, Model model) {
        StudentApplication application
            = studentApplicationRepo.findById(studentId).get();
        application.setStatus(ENROLLED);

        Student student = application.getStudent();
        student.setEnrollmentDate(new Date());
        //String registrationNumber = generateRegistrationNumber(application);
        //student.setRegistrationNumber(registrationNumber);
        application.setComments("Student with email '" 
            + application.getEmail()+"' enrolled.");
        studentApplicationRepo.save(application);

        model.addAttribute("successMessage", "Student '"
            + application.getFirstName() + " " + application.getLastName()
            + "' enrolled successfully with registration number '"
            + student.getRegistrationNumber() + "'");

        return listStudentsReadyToEnroll(model);
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/admin/student-application/update")
    public String updateStudentDetails(Long id, Long studentId,
        String changeRequestType, AddressDTO dto, ContactDTO contactDto, Model model) {
        if ("Address".equals(changeRequestType)) {
            return updateStudentAddress(studentId, dto, model);
        } else {
            return updateStudentContact(studentId, contactDto, model);
        }
    }

    private String updateStudentContact(Long studentId, ContactDTO dto,
        Model model) {

        StudentApplication application = studentApplicationRepo
            .findById(studentId).get();
        Pair<Optional<Contact>, Optional<Contact>> contacts
            = getPrimarySecondaryContact(application.getStudentContactList());

        Contact primary = contacts.getFirst().isPresent()
            ? contacts.getFirst().get() : new Contact();
        setContactDetails(primary, dto.getCountryCode(), dto.getPhoneNumber(), 
            dto.getContactType());
        saveContact(application, primary, CONTACT_PRIMARY, 
            contacts.getFirst().isEmpty());
        
        Contact secondary = contacts.getSecond().isPresent()
            ? contacts.getSecond().get() : new Contact();
        setContactDetails(secondary, dto.getAltCountryCode(), 
            dto.getAltPhoneNumber(), dto.getAltContactType());
        saveContact(application, secondary, CONTACT_SECONDARY, 
            contacts.getFirst().isEmpty());
        
        studentApplicationRepo.save(application);
        model.addAttribute("successMessage", "Student contact number(s) updated successfully!");

        return changeRequestController.listChangeRequestsForAdmin("Open", model);
    }

    private String updateStudentAddress(Long studentId, AddressDTO dto, Model model) {
        StudentApplication application = studentApplicationRepo
            .findById(studentId).get();
        Pair<Optional<Address>, Optional<Address>> addresses
            = getPermanentTemporaryAddresses(application.getStudentAddressList());

        Address permanent = addresses.getFirst().isPresent()
            ? addresses.getFirst().get() : new Address();
        setAddressDetails(permanent, dto.getLine1(), dto.getLine2(), 
            dto.getCity(), dto.getState(), dto.getCountry());
        saveAddress(application, permanent, ADDRESS_PERMANENT,
            !addresses.getFirst().isPresent());

        Address temporary = addresses.getSecond().isPresent()
            ? addresses.getSecond().get() : new Address();
        if (!dto.isTmpSameAsAbove()) {
            setAddressDetails(temporary, dto.getTmpLine1(), dto.getTmpLine2(), 
            dto.getTmpCity(), dto.getTmpState(), dto.getTmpCountry());
            addressRepo.save(temporary);
            saveAddress(application, temporary, ADDRESS_TEMPORARY,
                !addresses.getSecond().isPresent());
        }
        studentApplicationRepo.save(application);
        model.addAttribute("successMessage", "Student address updated successfully!");
        return changeRequestController.listChangeRequestsForAdmin("Open", model);
    }

    private void saveContact(StudentApplication application, Contact contact,
        String contactType, boolean isNew) {
        contactRepo.save(contact);
        if (isNew) {
            StudentContact studentContact = new StudentContact();
            studentContact.setContactType(contactType);
            studentContact.setContact(contact);
            studentContact.setStudentApplication(application);
            studentContact.setStudentContactPK(new StudentContactPK(
            application.getId(), contact.getId()));
            studentContactRepo.save(studentContact);
            application.getStudentContactList().add(studentContact);
        }
    }

    private void saveAddress(StudentApplication application, Address address,
        String addressType, boolean isNew) {
        addressRepo.save(address);
        if (isNew) {
            StudentAddress studentAddress = new StudentAddress();
            studentAddress.setStudentAddressPK(new StudentAddressPK(
                application.getId(), address.getId()));
            studentAddress.setAddressType(addressType);
            studentAddress.setAddress(address);
            studentAddress.setStudentApplication(application);
            studentAddressRepo.save(studentAddress);
            application.getStudentAddressList().add(studentAddress);
        }
    }

    @PostMapping("/admin/student-application/edit")
    public String editStudentDetails(Long id, Long studentId,
        String changeRequestType, Model model) {

        model.addAttribute("id", id);
        model.addAttribute("studentId", studentId);
        model.addAttribute("changeRequestType", changeRequestType);

        ChangeRequest changeRequest = changeRequestRepo.findById(id).get();
        model.addAttribute("studentRemarks", changeRequest.getStudentRemarks());

        StudentApplication application = studentApplicationRepo
            .findById(studentId).get();

        Pair<Optional<Contact>, Optional<Contact>> contacts = getPrimarySecondaryContact(
            application.getStudentContactList());
        Pair<Optional<Address>, Optional<Address>> addresses
            = getPermanentTemporaryAddresses(application.getStudentAddressList());

        model.addAttribute("app", application);
        if (contacts.getFirst().isPresent()) {
            Contact primary = contacts.getFirst().get();
            model.addAttribute("primaryContact", primary);
        }
        if (contacts.getSecond().isPresent()) {
            Contact secondary = contacts.getFirst().get();
            model.addAttribute("alternateContact", secondary);
        }

        if (addresses.getFirst().isPresent()) {
            model.addAttribute("permanentAddress", addresses.getFirst().get());
        }
        if (addresses.getSecond().isPresent()) {
            model.addAttribute("temporaryAddress", addresses.getSecond().get());
        }
        return "admin/edit-student-details";
    }
    
    @GetMapping("/admin/list-enrolled-students")
    public String listEnrolledStudents(Model model) {
        List<StudentInfo> enrolledStudentList = studentInfoRepo.findAll();
        model.addAttribute("enrolledStudentList", enrolledStudentList);
        return "admin/list-enrolled-students";
    }
    
}
