package bits.pilani.sem2.dda.assignment1.repository;

import bits.pilani.sem2.dda.assignment1.entity.StudentApplication;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author yogeshk
 */
public interface StudentApplicationRepository extends JpaRepository<StudentApplication, Long> {

    Optional<StudentApplication> findByEmail(String email);

    List<StudentApplication> findByStatus(String status);

    @Procedure(name = "generate_otp")
    Integer generateOtp(@Param("length") Integer length);
}
