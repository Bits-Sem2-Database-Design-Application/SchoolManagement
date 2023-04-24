package bits.pilani.sem2.dda.assignment1.entity;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 *
 * @author yogeshk
 */
@Embeddable
public class StudentAddressPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "STUDENT_ID")
    private long studentId;
    @Basic(optional = false)
    @Column(name = "ADDRESS_ID")
    private long addressId;

    public StudentAddressPK() {
    }

    public StudentAddressPK(long studentId, long addressId) {
        this.studentId = studentId;
        this.addressId = addressId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) studentId;
        hash += (int) addressId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentAddressPK)) {
            return false;
        }
        StudentAddressPK other = (StudentAddressPK) object;
        if (this.studentId != other.studentId) {
            return false;
        }
        if (this.addressId != other.addressId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bits.pilani.sem2.dda.assignment1.entity.StudentAddressPK[ studentId=" + studentId + ", addressId=" + addressId + " ]";
    }
    
}
