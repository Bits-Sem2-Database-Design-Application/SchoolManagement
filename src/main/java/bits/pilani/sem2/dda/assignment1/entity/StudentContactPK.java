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
public class StudentContactPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "STUDENT_ID")
    private long studentId;
    @Basic(optional = false)
    @Column(name = "CONTACT_ID")
    private long contactId;

    public StudentContactPK() {
    }

    public StudentContactPK(long studentId, long contactId) {
        this.studentId = studentId;
        this.contactId = contactId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) studentId;
        hash += (int) contactId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentContactPK)) {
            return false;
        }
        StudentContactPK other = (StudentContactPK) object;
        if (this.studentId != other.studentId) {
            return false;
        }
        if (this.contactId != other.contactId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bits.pilani.sem2.dda.assignment1.entity.StudentContactPK[ studentId=" + studentId + ", contactId=" + contactId + " ]";
    }
    
}
