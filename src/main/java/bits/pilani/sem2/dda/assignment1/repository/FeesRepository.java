package bits.pilani.sem2.dda.assignment1.repository;

import bits.pilani.sem2.dda.assignment1.entity.Fees;
import bits.pilani.sem2.dda.assignment1.entity.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author yogeshk
 */
public interface FeesRepository extends JpaRepository<Fees, Long>{
    public List<Fees> findByStudentId(Student student);
}
