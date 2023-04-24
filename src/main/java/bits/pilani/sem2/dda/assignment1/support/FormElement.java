package bits.pilani.sem2.dda.assignment1.support;

import static bits.pilani.sem2.dda.assignment1.Constants.EMPTY;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;

/**
 *
 * @author yogeshk
 */
@Data
public class FormElement {
    
    private String label;
    private String inputType;
    private String name;
    private String error;
    private String value;
    private Map<String, String> properties = new HashMap<>(); 
    
    public FormElement(String label, String inputType, String name) {
        this.label = label;
        this.inputType = inputType;
        this.name = name;
    }
}
