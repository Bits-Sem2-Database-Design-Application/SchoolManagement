package bits.pilani.sem2.dda.assignment1.controller;

import bits.pilani.sem2.dda.assignment1.support.SecurityContext;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String welcome(Model model) {
        model.addAttribute("isHomePage", Boolean.TRUE); //for activating menu
        return "index";
    }

    @GetMapping("/login")
    public String login(String error, Model model) {
        if (error != null && "loginFailed".equals(error)) {
            model.addAttribute("errorMessage", "Login Failed! Invalid credentials.");
        }
        return ControllerHelper.login(model);
    }
    
    @GetMapping("/home")
    public String home(String error, HttpSession session, Model model) {
        if (error != null && "accessDenied".equals(error)) {
            model.addAttribute("errorMessage", "Access Denied! No access to request page.");
        }
        boolean isAdmin = SecurityContext.isAdmin();
        if (isAdmin) {
            session.setAttribute("firstName","Admin");
        } else {
            session.setAttribute("firstName", SecurityContext.getUserFirstName());
        }
        return isAdmin ? "/admin/home" : "/student/home";
    }
}
