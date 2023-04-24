package bits.pilani.sem2.dda.assignment1.controller;

import static bits.pilani.sem2.dda.assignment1.Constants.EMPTY;
import bits.pilani.sem2.dda.assignment1.entity.StudentApplication;
import bits.pilani.sem2.dda.assignment1.entity.UserAccount;
import bits.pilani.sem2.dda.assignment1.repository.StudentApplicationRepository;
import bits.pilani.sem2.dda.assignment1.repository.UserAccountRepository;
import java.util.Optional;
import java.util.Random;
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
@RequestMapping("/reset-password")
public class ResetPasswordController {
    
    @Autowired
    private StudentApplicationRepository studentApplicationRepo;
    
    @Autowired 
    private UserAccountRepository userAccountRepo;
    
    @GetMapping({"", "/"})
    public String showResetPasswordForm(Model model) {
        model.addAttribute("formAction", "/reset-password/email");
        return "reset-password";
    }
    
    @Transactional
    @PostMapping("/email")
    public String generateAndEmailResetPasswordOtp(String email, Model model) {
        if (email == null || EMPTY.equals(email.trim())) {
            model.addAttribute("errorEmail", "Missing email!");
            return showResetPasswordForm(model);
        }
        //Check it is registered email
        Optional<StudentApplication> opApp = studentApplicationRepo.findByEmail(email);
        if (opApp.isEmpty()) {
            //Set error if not registered email
            model.addAttribute("email", email);
            model.addAttribute("errorEmail", "Invalid email");
            return showResetPasswordForm(model);
        }
        //Generate OTP and store in DB
        int otp = studentApplicationRepo.generateOtp(4); //new Random().nextInt(1000, 9999);
        System.out.println("OTP for Reset Password generated using stored procedure: "+otp);
        StudentApplication app = opApp.get();
        app.setOtp(otp);
        studentApplicationRepo.save(app);
        //TODO Send OTP to email.
        return showResetPasswordForm(email, model);
    }
    
    private String showResetPasswordForm(String email, Model model) {
        model.addAttribute("email", email);
        model.addAttribute("emailReadonly", "readonly");
        model.addAttribute("formAction", "/reset-password/otp");
        model.addAttribute("otpDisplay", "display");
        return "reset-password";
    }
    
    private String showResetPasswordForm(String email, String otp, Model model) {
        model.addAttribute("email", email);
        model.addAttribute("emailReadonly", "readonly");
        model.addAttribute("formAction", "/reset-password/new-password");
        model.addAttribute("newPasswordDisplay", "display");
        return "reset-password";
    }
    
    @PostMapping("/otp")
    public String validateResetPasswordOtp(String email, String otp, Model model) {
        if (otp == null || EMPTY.equals(otp.trim())) {
            model.addAttribute("errorOTP", "Missing OTP!");
            return showResetPasswordForm(email, model);
        }
        //Check it is registered email
        StudentApplication app = studentApplicationRepo
            .findByEmail(email).get();
        if (!String.valueOf(app.getOtp()).equals(otp)) {
            //Validate OTP against OTP stored in DB
            model.addAttribute("errorOTP", "Invalid OTP!");
            return showResetPasswordForm(email, model);
        }        
        return showResetPasswordForm(email, otp, model);
    }
    
    @Transactional
    @PostMapping("/new-password")
    public String resetPassword(String email, String newPassword, 
        String confirmPassword, Model model) {
        if (newPassword == null || EMPTY.equals(newPassword.trim())) {
            model.addAttribute("errorNewPassword", "Missing New Password!");
            return showResetPasswordForm(email, EMPTY, model);
        }
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("errorConfirmPassword", "Mismatch confirm & new password!");
            return showResetPasswordForm(email, EMPTY, model);
        }
        UserAccount account = userAccountRepo.findUserAccountByUsername(email);
        if (account != null) {
            account.setPassword(newPassword);
            userAccountRepo.save(account);
            model.addAttribute("successMessage", "Password reset successful for '"+email+"'");
        } else {
            model.addAttribute("errorMessage", "Account not created for '"+email+"'");
        }
        return "login";
    }
}
