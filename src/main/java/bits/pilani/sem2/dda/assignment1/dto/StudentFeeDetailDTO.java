package bits.pilani.sem2.dda.assignment1.dto;

import lombok.Data;
import lombok.ToString;

/**
 *
 * @author yogeshk
 */
@Data
@ToString
public class StudentFeeDetailDTO {
    
    private Long id;
    private String firstName;
    private String lastName;
    private String program;
    private String status;
    private String bank;
    private String transactionDate;
    private String transactionNumber;
    private String transactionAmount;
    
}
