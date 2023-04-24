package bits.pilani.sem2.dda.assignment1.support;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 *
 * @author yogeshk
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, 
        HttpServletResponse response, 
        AccessDeniedException accessDeniedException) 
        throws IOException, ServletException {
        System.out.println("Access Denied: "+ request.getRequestURI());
    }
}
