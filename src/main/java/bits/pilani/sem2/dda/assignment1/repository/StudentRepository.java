package bits.pilani.sem2.dda.assignment1.repository;

import bits.pilani.sem2.dda.assignment1.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author yogeshk
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
}
