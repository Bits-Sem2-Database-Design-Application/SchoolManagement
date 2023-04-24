package bits.pilani.sem2.dda.assignment1.repository;

import bits.pilani.sem2.dda.assignment1.entity.StudentAddress;
import bits.pilani.sem2.dda.assignment1.entity.StudentAddressPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author yogeshk
 */
public interface StudentAddressRepository extends JpaRepository<StudentAddress, StudentAddressPK>{
    
}
