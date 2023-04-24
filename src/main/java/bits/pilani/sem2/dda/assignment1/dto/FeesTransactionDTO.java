package bits.pilani.sem2.dda.assignment1.dto;

import lombok.Data;
import lombok.ToString;

/**
 *
 * @author yogeshk
 */
@Data
@ToString
public class FeesTransactionDTO {

    private Long studentId;
    private String bank;
    private String transactionDate;
    private String transactionNumber;
    private Long transactionAmount; 
}
