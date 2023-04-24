package bits.pilani.sem2.dda.assignment1.entity;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 *
 * @author yogeshk
 */
@Entity
@Table(name = "CHANGE_REQUEST")
@NamedQueries({
    @NamedQuery(name = "ChangeRequest.findAll", query = "SELECT c FROM ChangeRequest c"),
    @NamedQuery(name = "ChangeRequest.findById", query = "SELECT c FROM ChangeRequest c WHERE c.id = :id"),
    @NamedQuery(name = "ChangeRequest.findByChangeRequestType", query = "SELECT c FROM ChangeRequest c WHERE c.changeRequestType = :changeRequestType"),
    @NamedQuery(name = "ChangeRequest.findByChangeRequestDate", query = "SELECT c FROM ChangeRequest c WHERE c.changeRequestDate = :changeRequestDate"),
    @NamedQuery(name = "ChangeRequest.findByStudentRemarks", query = "SELECT c FROM ChangeRequest c WHERE c.studentRemarks = :studentRemarks"),
    @NamedQuery(name = "ChangeRequest.findByAdminRemarks", query = "SELECT c FROM ChangeRequest c WHERE c.adminRemarks = :adminRemarks"),
    @NamedQuery(name = "ChangeRequest.findByStatus", query = "SELECT c FROM ChangeRequest c WHERE c.status = :status")})
public class ChangeRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "CHANGE_REQUEST_TYPE")
    private String changeRequestType;
    @Basic(optional = false)
    @Column(name = "CHANGE_REQUEST_DATE")
    @Temporal(TemporalType.DATE)
    private Date changeRequestDate;
    @Basic(optional = false)
    @Column(name = "STUDENT_REMARKS")
    private String studentRemarks;
    @Basic(optional = false)
    @Column(name = "ADMIN_REMARKS")
    private String adminRemarks;
    @Basic(optional = false)
    private String status;
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Student studentId;

    public ChangeRequest() {
    }

    public ChangeRequest(Long id) {
        this.id = id;
    }

    public ChangeRequest(Long id, String changeRequestType, Date changeRequestDate, String studentRemarks, String adminRemarks, String status) {
        this.id = id;
        this.changeRequestType = changeRequestType;
        this.changeRequestDate = changeRequestDate;
        this.studentRemarks = studentRemarks;
        this.adminRemarks = adminRemarks;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChangeRequestType() {
        return changeRequestType;
    }

    public void setChangeRequestType(String changeRequestType) {
        this.changeRequestType = changeRequestType;
    }

    public Date getChangeRequestDate() {
        return changeRequestDate;
    }

    public void setChangeRequestDate(Date changeRequestDate) {
        this.changeRequestDate = changeRequestDate;
    }

    public String getStudentRemarks() {
        return studentRemarks;
    }

    public void setStudentRemarks(String studentRemarks) {
        this.studentRemarks = studentRemarks;
    }

    public String getAdminRemarks() {
        return adminRemarks;
    }

    public void setAdminRemarks(String adminRemarks) {
        this.adminRemarks = adminRemarks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
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
        if (!(object instanceof ChangeRequest)) {
            return false;
        }
        ChangeRequest other = (ChangeRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bits.pilani.sem2.dda.assignment1.entity.ChangeRequest[ id=" + id + " ]";
    }
    
}
