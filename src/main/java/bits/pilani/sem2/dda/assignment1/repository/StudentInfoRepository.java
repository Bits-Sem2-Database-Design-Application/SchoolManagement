package bits.pilani.sem2.dda.assignment1.repository;

import bits.pilani.sem2.dda.assignment1.entity.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author yogeshk
 */
public interface StudentInfoRepository extends JpaRepository<StudentInfo, Long>{
}
