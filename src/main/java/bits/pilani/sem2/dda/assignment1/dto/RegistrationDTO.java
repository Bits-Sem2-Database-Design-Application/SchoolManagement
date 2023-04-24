package bits.pilani.sem2.dda.assignment1.dto;

import lombok.Data;
import lombok.ToString;

/**
 *
 * @author yogeshk
 */
@Data
@ToString
public class RegistrationDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
}
