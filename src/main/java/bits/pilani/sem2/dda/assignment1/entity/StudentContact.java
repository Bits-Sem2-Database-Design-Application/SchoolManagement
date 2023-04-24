package bits.pilani.sem2.dda.assignment1.entity;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 *
 * @author yogeshk
 */
@Entity
@Table(name = "STUDENT_CONTACT")
@NamedQueries({
    @NamedQuery(name = "StudentContact.findAll", query = "SELECT s FROM StudentContact s"),
    @NamedQuery(name = "StudentContact.findByStudentId", query = "SELECT s FROM StudentContact s WHERE s.studentContactPK.studentId = :studentId"),
    @NamedQuery(name = "StudentContact.findByContactId", query = "SELECT s FROM StudentContact s WHERE s.studentContactPK.contactId = :contactId"),
    @NamedQuery(name = "StudentContact.findByContactType", query = "SELECT s FROM StudentContact s WHERE s.contactType = :contactType")})
public class StudentContact implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StudentContactPK studentContactPK;
    @Basic(optional = false)
    @Column(name = "CONTACT_TYPE")
    private String contactType;
    @JoinColumn(name = "CONTACT_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Contact contact;
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private StudentApplication studentApplication;

    public StudentContact() {
    }

    public StudentContact(StudentContactPK studentContactPK) {
        this.studentContactPK = studentContactPK;
    }

    public StudentContact(StudentContactPK studentContactPK, String contactType) {
        this.studentContactPK = studentContactPK;
        this.contactType = contactType;
    }

    public StudentContact(long studentId, long contactId) {
        this.studentContactPK = new StudentContactPK(studentId, contactId);
    }

    public StudentContactPK getStudentContactPK() {
        return studentContactPK;
    }

    public void setStudentContactPK(StudentContactPK studentContactPK) {
        this.studentContactPK = studentContactPK;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public StudentApplication getStudentApplication() {
        return studentApplication;
    }

    public void setStudentApplication(StudentApplication studentApplication) {
        this.studentApplication = studentApplication;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentContactPK != null ? studentContactPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentContact)) {
            return false;
        }
        StudentContact other = (StudentContact) object;
        if ((this.studentContactPK == null && other.studentContactPK != null) || (this.studentContactPK != null && !this.studentContactPK.equals(other.studentContactPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bits.pilani.sem2.dda.assignment1.entity.StudentContact[ studentContactPK=" + studentContactPK + " ]";
    }
    
}
