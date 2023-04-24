package bits.pilani.sem2.dda.assignment1.dto;

import lombok.Data;
import lombok.ToString;
/**
 *
 * @author yogeshk
 */
@Data
@ToString
public class StudentApplicationDTO {
    
    private String firstName;
    private String lastName;
    private String birthDate;
    private String gender;
    private String email;
    private String program;
    
    private String line1;
    private String line2;
    private String city;
    private String state;
    private String country;
    
    private boolean tmpSameAsAbove;
    
    private String tmpLine1;
    private String tmpLine2;
    private String tmpCity;
    private String tmpState;
    private String tmpCountry;
    
    private String countryCode;
    private String phoneNumber;
    private String contactType;
    private String altCountryCode;
    private String altPhoneNumber;
    private String altContactType;
}
