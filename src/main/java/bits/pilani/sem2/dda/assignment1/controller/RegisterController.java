package bits.pilani.sem2.dda.assignment1.controller;

import bits.pilani.sem2.dda.assignment1.dto.RegistrationDTO;
import bits.pilani.sem2.dda.assignment1.entity.StudentApplication;
import bits.pilani.sem2.dda.assignment1.entity.UserAccount;
import bits.pilani.sem2.dda.assignment1.repository.StudentApplicationRepository;
import bits.pilani.sem2.dda.assignment1.repository.StudentRepository;
import bits.pilani.sem2.dda.assignment1.repository.UserAccountRepository;
import bits.pilani.sem2.dda.assignment1.support.FormElement;
import static bits.pilani.sem2.dda.assignment1.support.FormHelper.getRegistrationFormElements;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author yogeshk
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private StudentApplicationRepository studentApplicationRepo;

    @Autowired
    private UserAccountRepository userAccountRepo;
    
    @Autowired
    private StudentRepository studentRepo;
    

    @GetMapping({"", "/"})
    public String showRegistrationForm(Model model) {
        model.addAttribute("formAction", "/register");
        model.addAttribute("formHeader", "::Student Account Registration Form ");
        model.addAttribute("submitButton", "Register");
        if (!model.containsAttribute("formElementList")) {
            Map<String, FormElement> elementMap = getRegistrationFormElements();
            model.addAttribute("formElementList", elementMap.values());
        }
        return "register";
    }
    
    @Transactional
    @PostMapping({"", "/"})
    public String register(RegistrationDTO dto, Model model) {
        Optional<StudentApplication> optionalApplication
            = studentApplicationRepo.findByEmail(dto.getEmail());
        if (optionalApplication.isEmpty()) {
            model.addAttribute("errorMessage", "Invalid email! Student with email '"
                + dto.getEmail() + "' not enrolled.");

            model.addAttribute("formElementList",
                getRegistrationFormElements(dto, new HashMap<>()).values());
            return showRegistrationForm(model);
        }
        StudentApplication application = optionalApplication.get();
        Map<String, String> errorMap = validateRegistrationDTO(dto, application);
        if (!errorMap.isEmpty()) {
            model.addAttribute("errorMessage", "Invalid Registration Details !!!");
            model.addAttribute("formElementList",
                getRegistrationFormElements(dto, errorMap).values());
            return showRegistrationForm(model);
        }

        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(dto.getEmail());
        userAccount.setPassword(dto.getPassword());
        userAccount.setRole("STUDENT");
        userAccountRepo.save(userAccount);
        
        application.getStudent().setUserId(userAccount);
        studentApplicationRepo.save(application);
        
        model.addAttribute("successMessage", "Registration Successfull! ");
        model.addAttribute("successMessage1", "User account with email '"
            + dto.getEmail() + "' setup successfully.");
        
        return ControllerHelper.login(model);
    }

    private Map<String, String> validateRegistrationDTO(RegistrationDTO dto,
        StudentApplication application) {
        Map<String, String> errorMap = new HashMap<>();
        if (dto.getFirstName() == null
            || !dto.getFirstName().equals(application.getFirstName())) {
            errorMap.put("firstName", "Not matching registered details");
        }
        if (dto.getLastName() == null
            || !dto.getLastName().equals(application.getLastName())) {
            errorMap.put("lastName", "Not matching registered details");
        }

        if (dto.getPassword() == null || dto.getPassword().trim().length() < 6) {
            errorMap.put("password", "Expected minimum 6 chars");
        }

        if (dto.getPassword() != null
            && !dto.getPassword().equals(dto.getConfirmPassword())) {
            errorMap.put("confirmPassword", "Must match password");
        }

        return errorMap;
    }
}
