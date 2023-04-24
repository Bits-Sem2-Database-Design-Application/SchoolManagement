package bits.pilani.sem2.dda.assignment1.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;

/**
 *
 * @author yogeshk
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c"),
    @NamedQuery(name = "Contact.findById", query = "SELECT c FROM Contact c WHERE c.id = :id"),
    @NamedQuery(name = "Contact.findByCountryCode", query = "SELECT c FROM Contact c WHERE c.countryCode = :countryCode"),
    @NamedQuery(name = "Contact.findByContactNumber", query = "SELECT c FROM Contact c WHERE c.contactNumber = :contactNumber"),
    @NamedQuery(name = "Contact.findByContactType", query = "SELECT c FROM Contact c WHERE c.contactType = :contactType")})
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "COUNTRY_CODE")
    private int countryCode;
    @Basic(optional = false)
    @Column(name = "CONTACT_NUMBER")
    private long contactNumber;
    @Column(name = "CONTACT_TYPE")
    private String contactType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contact")
    private List<StudentContact> studentContactList;

    public Contact() {
        studentContactList = new ArrayList<>();
    }

    public Contact(Long id) {
        this.id = id;
    }

    public Contact(Long id, int countryCode, long contactNumber) {
        this.id = id;
        this.countryCode = countryCode;
        this.contactNumber = contactNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactType() {
        return contactType;
    }
    
    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public List<StudentContact> getStudentContactList() {
        return studentContactList;
    }

    public void setStudentContactList(List<StudentContact> studentContactList) {
        this.studentContactList = studentContactList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contact)) {
            return false;
        }
        Contact other = (Contact) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bits.pilani.sem2.dda.assignment1.entity.Contact[ id=" + id + " ]";
    }
    
}
