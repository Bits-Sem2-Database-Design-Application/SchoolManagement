package bits.pilani.sem2.dda.assignment1.dto;

import lombok.Data;
import lombok.ToString;

/**
 *
 * @author yogeshk
 */
@Data
@ToString
public class AddressDTO {

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

}
