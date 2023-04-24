package bits.pilani.sem2.dda.assignment1.support;

import bits.pilani.sem2.dda.assignment1.dto.CustomUserDetails;
import bits.pilani.sem2.dda.assignment1.entity.StudentApplication;
import bits.pilani.sem2.dda.assignment1.entity.UserAccount;
import bits.pilani.sem2.dda.assignment1.repository.StudentApplicationRepository;
import bits.pilani.sem2.dda.assignment1.repository.UserAccountRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author yogeshk
 */
@Service
public class CustomUserDetailService implements UserDetailsService {
    
    @Autowired
    private UserAccountRepository repo;
    
    @Autowired 
    private StudentApplicationRepository studentApplicationRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = repo.findUserAccountByUsername(username);
        if (userAccount == null) {
            System.out.println(username+ " not found!");
            throw new UsernameNotFoundException(username+ " not found!");
        }
        String firstName = "";
        Optional<StudentApplication> opStudent = 
            studentApplicationRepo.findByEmail(userAccount.getUsername());
        if (opStudent.isPresent()) {
            firstName = opStudent.get().getFirstName();
        }
        UserDetails user = User
            .withUsername(userAccount.getUsername())
            .password(userAccount.getPassword())
            .authorities("ROLE_"+userAccount.getRole())
            .build();
        return new CustomUserDetails(user, userAccount.getRole(), firstName);
    }
}
