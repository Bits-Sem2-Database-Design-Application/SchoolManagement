package bits.pilani.sem2.dda.assignment1.repository;

import bits.pilani.sem2.dda.assignment1.entity.StudentContact;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author yogeshk
 */
public interface StudentContactRepository extends JpaRepository<StudentContact, Long> {
    
}
