package bits.pilani.sem2.dda.assignment1.support;

import lombok.Data;

/**
 *
 * @author yogeshk
 */
@Data
public class TextAreaElement extends FormElement {
    
    private int rows;
    private int columns;
    
    public TextAreaElement(String label, String inputType, String name, 
        int rows, int columns) {
        super(label, inputType, name);
        this.rows = rows;
        this.columns = columns;
    }
}
