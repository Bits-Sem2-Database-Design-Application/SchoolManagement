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
@Table(name = "STUDENT_ADDRESS")
@NamedQueries({
    @NamedQuery(name = "StudentAddress.findAll", query = "SELECT s FROM StudentAddress s"),
    @NamedQuery(name = "StudentAddress.findByStudentId", query = "SELECT s FROM StudentAddress s WHERE s.studentAddressPK.studentId = :studentId"),
    @NamedQuery(name = "StudentAddress.findByAddressId", query = "SELECT s FROM StudentAddress s WHERE s.studentAddressPK.addressId = :addressId"),
    @NamedQuery(name = "StudentAddress.findByAddressType", query = "SELECT s FROM StudentAddress s WHERE s.addressType = :addressType")})
public class StudentAddress implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StudentAddressPK studentAddressPK;
    
    @Basic(optional = false)
    @Column(name = "ADDRESS_TYPE")
    private String addressType;
    
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Address address;
    
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private StudentApplication studentApplication;

    public StudentAddress() {
    }

    public StudentAddress(StudentAddressPK studentAddressPK) {
        this.studentAddressPK = studentAddressPK;
    }

    public StudentAddress(StudentAddressPK studentAddressPK, String addressType) {
        this.studentAddressPK = studentAddressPK;
        this.addressType = addressType;
    }

    public StudentAddress(long studentId, long addressId) {
        this.studentAddressPK = new StudentAddressPK(studentId, addressId);
    }

    public StudentAddressPK getStudentAddressPK() {
        return studentAddressPK;
    }

    public void setStudentAddressPK(StudentAddressPK studentAddressPK) {
        this.studentAddressPK = studentAddressPK;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
        hash += (studentAddressPK != null ? studentAddressPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentAddress)) {
            return false;
        }
        StudentAddress other = (StudentAddress) object;
        if ((this.studentAddressPK == null && other.studentAddressPK != null) || (this.studentAddressPK != null && !this.studentAddressPK.equals(other.studentAddressPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bits.pilani.sem2.dda.assignment1.entity.StudentAddress[ studentAddressPK=" + studentAddressPK + " ]";
    }
    
}
