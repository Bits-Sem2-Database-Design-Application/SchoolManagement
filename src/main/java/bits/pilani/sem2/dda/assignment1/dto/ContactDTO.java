package bits.pilani.sem2.dda.assignment1.dto;

import lombok.Data;
import lombok.ToString;

/**
 *
 * @author yogeshk
 */
@Data
@ToString
public class ContactDTO {
    private int countryCode;
    private long phoneNumber;
    private String contactType;
    
    private int altCountryCode;
    private long altPhoneNumber;
    private String altContactType;
}
