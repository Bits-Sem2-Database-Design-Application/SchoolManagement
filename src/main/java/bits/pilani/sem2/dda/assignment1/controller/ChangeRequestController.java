package bits.pilani.sem2.dda.assignment1.controller;

import static bits.pilani.sem2.dda.assignment1.Constants.CR_CLOSED;
import static bits.pilani.sem2.dda.assignment1.Constants.CR_OPEN;
import static bits.pilani.sem2.dda.assignment1.Constants.EMPTY;
import static bits.pilani.sem2.dda.assignment1.controller.ControllerHelper.getPermanentTemporaryAddresses;
import static bits.pilani.sem2.dda.assignment1.controller.ControllerHelper.getPrimarySecondaryContact;
import bits.pilani.sem2.dda.assignment1.entity.Address;
import bits.pilani.sem2.dda.assignment1.entity.ChangeRequest;
import bits.pilani.sem2.dda.assignment1.entity.Contact;
import bits.pilani.sem2.dda.assignment1.entity.StudentApplication;
import bits.pilani.sem2.dda.assignment1.repository.ChangeRequestRepository;
import bits.pilani.sem2.dda.assignment1.repository.StudentApplicationRepository;
import static bits.pilani.sem2.dda.assignment1.support.FormHelper.getPostChangeRequestForm;
import bits.pilani.sem2.dda.assignment1.support.SecurityContext;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author yogeshk
 */
@Controller
public class ChangeRequestController {

    @Autowired
    private ChangeRequestRepository changeRequestRepo;

    @Autowired
    private StudentApplicationRepository studentApplicationRepo;
        
    @PostMapping("/admin/change-request/close")
    public String closeChangeRequest(Long id, String adminRemarks, Model model) {
        
        if (adminRemarks == null || EMPTY.equals(adminRemarks.trim())) {
            model.addAttribute("errorMessage", "Admin Remarks are required for closing change request!");
            return viewChangeRequest(id, model);
        }
        
        ChangeRequest changeRequest = changeRequestRepo.findById(id).get();
        changeRequest.setStatus(CR_CLOSED);
        changeRequest.setAdminRemarks(adminRemarks);
        changeRequestRepo.save(changeRequest);
        
        return listChangeRequestsForAdmin("Open", model);
    }
    
    @GetMapping("/admin/change-request/view")
    public String viewChangeRequest(Long id, Model model) {
        
        Optional<ChangeRequest> optional = changeRequestRepo.findById(id);
        if (optional.isEmpty()) {
            model.addAttribute("errorMessage", "Change Request Not Found!");
            return listChangeRequestsForAdmin("Open", model);
        }
        ChangeRequest changeRequest = optional.get();
        Pair<Optional<Contact>, Optional<Contact>> contacts = 
            getPrimarySecondaryContact(changeRequest.getStudentId()
                .getStudentApplication().getStudentContactList());   
        
        Pair<Optional<Address>, Optional<Address>> addresses
            = getPermanentTemporaryAddresses(changeRequest.getStudentId()
                .getStudentApplication().getStudentAddressList());
        if (addresses.getFirst().isPresent()) {
            model.addAttribute("permanentAddress", addresses.getFirst().get());
        }
        
        if (addresses.getSecond().isPresent()) {
            model.addAttribute("temporaryAddress", addresses.getSecond().get());
        }
        model.addAttribute("changeRequest", changeRequest);
        return "admin/view-change-request";
    }

    @GetMapping("/admin/change-request/list")
    public String listChangeRequestsForAdmin(String status, Model model) {
        List<ChangeRequest> changeRequestList = changeRequestRepo
            .findByStatus(status);

        model.addAttribute("formHeader", ":: List of " + status + " change requests");
        model.addAttribute("changeRequestList", changeRequestList);
        if (null == status) {
            model.addAttribute("errorMessage", "Invalid status '" + status + "'");
            return "admin/home";
        } else {
            switch (status) {
                case "Open" -> {
                    return "admin/list-open-change-requests";
                }
                case "Closed" -> {
                    return "admin/list-closed-change-requests";
                }
                default -> {
                    model.addAttribute("errorMessage", "Invalid status '" + status + "'");
                    return "admin/home";
                }
            }
        }
    }

    @GetMapping("/student/change-request/list")
    public String listChangeRequests(String status, Model model) {
        StudentApplication application = studentApplicationRepo
            .findByEmail(SecurityContext.getUsername()).get();

        List<ChangeRequest> changeRequestList = changeRequestRepo
            .findByStatusAndStudentId_Id(status, application.getId());

        model.addAttribute("formHeader", ":: List of " + status + " change requests");
        model.addAttribute("changeRequestList", changeRequestList);

        return "student/list-change-requests";
    }

    @GetMapping("/student/change-request/post")
    public String showPostChangeRequestForm(Model model) {
        if (!model.containsAttribute("formElementList")) {
            model.addAttribute("formElementList",
                getPostChangeRequestForm().values());
        }
        model.addAttribute("formHeader", ":: Post Change Request");
        model.addAttribute("formAction", "/student/change-request/post");
        model.addAttribute("submitButton", "Post");
        return "student/post-change-request";
    }

    @Transactional
    @PostMapping("/student/change-request/post")
    public String postChangeRequest(String changeRequestType,
        String studentRemarks, Model model) {

        Map<String, String> errorMap
            = validateChangeRequestForm(changeRequestType, studentRemarks);
        if (!errorMap.isEmpty()) {
            model.addAttribute("formElementList",
                getPostChangeRequestForm(errorMap).values());
            return showPostChangeRequestForm(model);
        }

        StudentApplication studentApplication = studentApplicationRepo
            .findByEmail(SecurityContext.getUsername()).get();

        ChangeRequest request = new ChangeRequest();
        request.setStudentId(studentApplication.getStudent());
        request.setChangeRequestType(changeRequestType);
        request.setStudentRemarks(studentRemarks);
        request.setAdminRemarks(EMPTY);
        request.setChangeRequestDate(new Date());
        request.setStatus(CR_OPEN);

        changeRequestRepo.save(request);

        model.addAttribute("successMessage", "Change request posted successfully!");
        return listChangeRequests("Open", model);
    }

    private Map<String, String> validateChangeRequestForm(String requestType,
        String remarks) {
        Map<String, String> errorMap = new HashMap<>();
        if (requestType == null || EMPTY.equals(requestType.trim())) {
            errorMap.put("changeRequestType", "Not selected!");
        }

        if (remarks == null || EMPTY.equals(remarks.trim())) {
            errorMap.put("studentRemarks", "Missing remarks!!!");
        }

        return errorMap;
    }
}
