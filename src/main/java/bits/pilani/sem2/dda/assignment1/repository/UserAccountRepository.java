package bits.pilani.sem2.dda.assignment1.repository;

import bits.pilani.sem2.dda.assignment1.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yogeshk
 */
@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long>{
    UserAccount findUserAccountByUsername(String username);
}
