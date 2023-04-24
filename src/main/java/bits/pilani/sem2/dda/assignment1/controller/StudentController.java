package bits.pilani.sem2.dda.assignment1.controller;

import static bits.pilani.sem2.dda.assignment1.Constants.EMPTY;
import bits.pilani.sem2.dda.assignment1.entity.StudentInfo;
import bits.pilani.sem2.dda.assignment1.support.FormElement;
import bits.pilani.sem2.dda.assignment1.entity.UserAccount;
import bits.pilani.sem2.dda.assignment1.repository.StudentInfoRepository;
import bits.pilani.sem2.dda.assignment1.repository.UserAccountRepository;
import bits.pilani.sem2.dda.assignment1.support.FormHelper;
import static bits.pilani.sem2.dda.assignment1.support.FormHelper.getChangePasswordFormElements;
import bits.pilani.sem2.dda.assignment1.support.SecurityContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author yogeshk
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    
    @Autowired
    private UserAccountRepository userRepo;
    
    @GetMapping("/change-password")
    public String showChangePasswordForm(Model model) {
        model.addAttribute("formAction", "/student/change-password");
        model.addAttribute("formHeader", ":: Change Password Form");
        model.addAttribute("submitButton", "Change Password");
        if (!model.containsAttribute("formElementList")) {
            Map<String, FormElement> elementMap
                = FormHelper.getChangePasswordFormElements();
            model.addAttribute("formElementList", elementMap.values());
        }
        return "student/change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(String oldPassword, String newPassword,
        String confirmPassword, Model model) {
        
        String email = SecurityContext.getUsername();
        UserAccount userAccount = userRepo.findUserAccountByUsername(email);
        Map<String, String> errorMap = validateChangePassword(oldPassword,
            newPassword, confirmPassword, userAccount.getPassword());

        if (!errorMap.isEmpty()) {
            model.addAttribute("errorMessage", "Invalid Password Details !!!");
            model.addAttribute("formElementList",
                getChangePasswordFormElements(errorMap).values());
            return showChangePasswordForm(model);
        }

        userAccount.setPassword(newPassword);
        userRepo.save(userAccount);
        model.addAttribute("successMessage", "Your password is changed successfully!");
        return "student/home";
    }

    private Map<String, String> validateChangePassword(String oldPassword,
        String newPassword, String confirmPassword, String dbPassword) {
        Map<String, String> errorMap = new HashMap<>();
        if (oldPassword == null || EMPTY.equals(oldPassword)) {
            errorMap.put("oldPassword", "Old password is required!");
        }
        if (!oldPassword.equals(dbPassword)) {
            errorMap.put("oldPassword", "Incorrect old password!");
        }
        
        if (newPassword == null || EMPTY.equals(newPassword)) {
            errorMap.put("newPassword", "New password is required!");
        }
        if (confirmPassword == null || EMPTY.equals(confirmPassword)) {
            errorMap.put("confirmPassword", "Confirm password is required!");
        }

        if (newPassword != null && !newPassword.equals(confirmPassword)) {
            errorMap.put("confirmPassword", "New and Confirm password must match!");
        }

        return errorMap;
    }
    
    @GetMapping({"", "/"})
    public String home() {
        return "student/home";
    }
}
