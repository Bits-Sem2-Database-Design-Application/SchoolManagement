package bits.pilani.sem2.dda.assignment1.repository;

import bits.pilani.sem2.dda.assignment1.entity.ChangeRequest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author yogeshk
 */
public interface ChangeRequestRepository extends JpaRepository<ChangeRequest, Long> {
    
    List<ChangeRequest> findByStatusAndStudentId_Id(String status, Long id);
    List<ChangeRequest> findByStatus(String status);
}
