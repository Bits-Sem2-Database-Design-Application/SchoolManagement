package bits.pilani.sem2.dda.assignment1.support;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author yogeshk
 */
@Data
public class DropdownElement extends FormElement {
    
    private List<Option> optionList = new ArrayList<>();
    
    public DropdownElement(String label, String inputType, String name) {
        super(label, inputType, name);
    }
    
    public void addOption(String value, String displayValue) {
        optionList.add(new Option(value, displayValue));
    }
    
    public List<Option> getOptionList() {
        return optionList;
    }
    
    class Option {
        String value;
        String displayValue;
        Option(String value, String displayValue) {
            this.value = value;
            this.displayValue = displayValue;
        }
        
        public String getValue() {
            return value;
        }
        
        public String getDisplayValue() {
            return displayValue;
        }
    }
    
    
    
}
