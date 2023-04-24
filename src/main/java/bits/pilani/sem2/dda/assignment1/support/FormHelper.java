package bits.pilani.sem2.dda.assignment1.support;

import bits.pilani.sem2.dda.assignment1.dto.RegistrationDTO;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author yogeshk
 */
public class FormHelper {

    public static Map<String, FormElement> getPostChangeRequestForm() {
        Map<String, FormElement> elementMap = new LinkedHashMap<>();
        DropdownElement crtypeElement = 
            new DropdownElement("* Type: ", "select", "changeRequestType");
        crtypeElement.addOption("Address", "Change Address");
        crtypeElement.addOption("Contact", "Change Contact");
        
        elementMap.put("changeRequestType", crtypeElement);
        elementMap.put("studentRemarks", 
            new TextAreaElement("* Remarks: ", "textarea", "studentRemarks", 10, 40) );
        
        
        return elementMap;
    }
    
    public static Map<String, FormElement> getPostChangeRequestForm(
        Map<String, String> errorMap) {
        Map<String, FormElement> elementMap = getPostChangeRequestForm();
        if (errorMap.containsKey("changeRequestType")) {
            elementMap.get("changeRequestType").setError(errorMap.get("changeRequestType"));
        }
        
        if (errorMap.containsKey("studentRemarks")) {
            elementMap.get("studentRemarks").setError(errorMap.get("studentRemarks"));
        }
        return elementMap;
    }

    public static Map<String, FormElement> getChangePasswordFormElements() {
        Map<String, FormElement> elementMap = new LinkedHashMap<>();
        elementMap.put("oldPassword", new FormElement("* Old Password: ", "password", "oldPassword"));
        elementMap.put("newPassword", new FormElement("* New Password: ", "password", "newPassword"));
        elementMap.put("confirmPassword", new FormElement("* Confirm Password: ", "password", "confirmPassword"));
        return elementMap;
    }

    public static Map<String, FormElement> getChangePasswordFormElements(
        Map<String, String> errorMap) {

        Map<String, FormElement> elementMap = getChangePasswordFormElements();
        if (errorMap.containsKey("oldPassword")) {
            elementMap.get("oldPassword").setError(errorMap.get("oldPassword"));
        }
        if (errorMap.containsKey("newPassword")) {
            elementMap.get("newPassword").setError(errorMap.get("newPassword"));
        }
        if (errorMap.containsKey("confirmPassword")) {
            elementMap.get("confirmPassword").setError(errorMap.get("confirmPassword"));
        }
        return elementMap;
    }

    public static Map<String, FormElement> getRegistrationFormElements() {
        Map<String, FormElement> elementMap = new LinkedHashMap<>();
        elementMap.put("firstName", new FormElement("* First name: ", "text", "firstName"));
        elementMap.put("lastName", new FormElement("* Last name: ", "text", "lastName"));
        elementMap.put("email", new FormElement("* Email: ", "email", "email"));
        elementMap.put("password", new FormElement("* Password: ", "password", "password"));
        elementMap.put("confirmPassword", new FormElement("* Confirm Password: ", "password", "confirmPassword"));
        return elementMap;
    }

    public static Map<String, FormElement> getRegistrationFormElements(
        RegistrationDTO dto, Map<String, String> errorMap) {
        Map<String, FormElement> elementMap = getRegistrationFormElements();
        FormElement firstName = elementMap.get("firstName");
        firstName.setValue(dto.getFirstName());
        firstName.setError(errorMap.get("firstName"));

        FormElement lastName = elementMap.get("lastName");
        lastName.setValue(dto.getLastName());
        lastName.setError(errorMap.get("lastName"));

        FormElement email = elementMap.get("email");
        email.setValue(dto.getEmail());
        email.setError(errorMap.get("email"));

        FormElement password = elementMap.get("password");
        password.setValue(dto.getPassword());
        password.setError(errorMap.get("password"));

        FormElement confirmPassword = elementMap.get("confirmPassword");
        confirmPassword.setValue(dto.getConfirmPassword());
        confirmPassword.setError(errorMap.get("confirmPassword"));

        return elementMap;
    }

}
