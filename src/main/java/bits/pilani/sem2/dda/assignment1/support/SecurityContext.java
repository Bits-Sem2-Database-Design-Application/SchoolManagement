package bits.pilani.sem2.dda.assignment1.support;

import bits.pilani.sem2.dda.assignment1.dto.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 *
 * @author yogeshk
 */
public class SecurityContext {
    
    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_STUDENT = "STUDENT";
    
    public static boolean isAdmin() {
        CustomUserDetails userDetails = getCustomUserDetails();
        if (userDetails != null) {
            return ROLE_ADMIN.equals(userDetails.getRole());
        }
        return false;
    }
    
    public static CustomUserDetails getCustomUserDetails() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails customUserDetails) {
            return customUserDetails;
        }
        return null;
    }
    
    public static String getUsername() {
        CustomUserDetails userDetails = getCustomUserDetails();
        if (userDetails != null) {
            return userDetails.getUsername();
        }
        return null;
    }
    
    public static String getUserFirstName() {
        CustomUserDetails userDetails = getCustomUserDetails();
        if (userDetails != null) {
            return userDetails.getFirstName();
        }
        return null;
    }
}
