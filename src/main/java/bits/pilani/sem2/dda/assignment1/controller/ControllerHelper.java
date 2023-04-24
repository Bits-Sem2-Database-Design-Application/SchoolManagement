package bits.pilani.sem2.dda.assignment1.controller;

import static bits.pilani.sem2.dda.assignment1.Constants.EMPTY;
import bits.pilani.sem2.dda.assignment1.dto.FeesTransactionDTO;
import bits.pilani.sem2.dda.assignment1.entity.Address;
import bits.pilani.sem2.dda.assignment1.entity.Contact;
import bits.pilani.sem2.dda.assignment1.entity.StudentAddress;
import bits.pilani.sem2.dda.assignment1.entity.StudentApplication;
import bits.pilani.sem2.dda.assignment1.entity.StudentContact;
import java.time.Year;
import java.util.List;
import java.util.Optional;
import org.springframework.data.util.Pair;
import org.springframework.ui.Model;

/**
 *
 * @author yogeshk
 */
public class ControllerHelper {
    
    public static Pair<Optional<Contact>, Optional<Contact>>
        getPrimarySecondaryContact(List<StudentContact> studentContactList) {
        if (studentContactList == null) {
            return Pair.of(Optional.empty(), Optional.empty());
        }
        Contact primary = null;
        Contact secondary = null;
        for (StudentContact studentContact : studentContactList) {
            if ("PRIMARY".equals(studentContact.getContactType())) {
                primary = studentContact.getContact();
            } else if ("SECONDARY".equals(studentContact.getContactType())) {
                secondary = studentContact.getContact();
            }
        }
        return Pair.of(
            primary == null ? Optional.empty() : Optional.of(primary),
            primary == null ? Optional.empty() : Optional.of(secondary));
    }

    public static Pair<Optional<Address>, Optional<Address>> getPermanentTemporaryAddresses(
        List<StudentAddress> list) {
        Address permanent = null;
        Address temporary = null;
        for (StudentAddress address : list) {
            if ("PERMANENT".equals(address.getAddressType())) {
                permanent = address.getAddress();
            } else if ("TEMPORARY".equals(address.getAddressType())) {
                temporary = address.getAddress();
            }
        }
        return Pair.of(
            permanent == null ? Optional.empty() : Optional.of(permanent),
            temporary == null ? Optional.empty() : Optional.of(temporary));
    }
    
    
    public static String login(Model model) {
        model.addAttribute("isLoginPage", Boolean.TRUE); //for activating menu
        return "login";
    }
    
    public static int toIntOtp(String otpString) {
        try {
            return Integer.parseInt(otpString);
        } catch (Exception e) {
            return -1;
        }
    }
    
    public static void setAddressDetails(Address address, String line1, String line2, String city, 
        String state, String country) {
        address.setLine1(line1);
        address.setLine2(line2);
        address.setCity(city);
        address.setState(state);
        address.setCountry(country);
    }
    
    public static void setContactDetails(Contact contact, int countryCode, 
        long phoneNumber, String contactType) {
        contact.setContactNumber(phoneNumber);
        contact.setCountryCode(countryCode);
        contact.setContactType(contactType);
    }
    
    public static String generateRegistrationNumber(StudentApplication application) {
        Year year = Year.now();
        Long programId = application.getProgramId().getId();
        String code = String.format("%1$3s", programId).replace(' ', '0');
        String id = String.format("%1$5s", application.getId()).replace(' ', '0');
        String registrationNumber = year.toString() + "_" + code + "_" + id;
        return registrationNumber;
    }
    
    public static boolean valid(FeesTransactionDTO dto, Model model) {
        boolean dataValid = true;
        if (dto.getBank() == null || EMPTY.equals(dto.getBank().trim())) {
            model.addAttribute("errorBank", "Bank name is required");
            dataValid = false;
        }
        if (dto.getTransactionAmount() == null || dto.getTransactionAmount() == 0) {
            model.addAttribute("errorTransactionAmount", "Transaction amount is required");
            dataValid = false;
        }

        if (dto.getTransactionDate() == null || EMPTY.equals(dto.getTransactionDate().trim())) {
            model.addAttribute("errorTransactionDate", "Transaction date is required");
            dataValid = false;
        }

        if (dto.getTransactionNumber() == null || EMPTY.equals(dto.getTransactionNumber().trim())) {
            model.addAttribute("errorTransactionNumber", "Transaction number is required");
            dataValid = false;
        }
        return dataValid;
    }
}
