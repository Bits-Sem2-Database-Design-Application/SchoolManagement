package bits.pilani.sem2.dda.assignment1.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 *
 * @author yogeshk
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Student.findById", query = "SELECT s FROM Student s WHERE s.id = :id"),
    @NamedQuery(name = "Student.findByRegistrationNumber", query = "SELECT s FROM Student s WHERE s.registrationNumber = :registrationNumber"),
    @NamedQuery(name = "Student.findByEnrollmentDate", query = "SELECT s FROM Student s WHERE s.enrollmentDate = :enrollmentDate"),
    @NamedQuery(name = "Student.findByCurrentSemister", query = "SELECT s FROM Student s WHERE s.currentSemister = :currentSemister"),
    @NamedQuery(name = "Student.findByProgramFees", query = "SELECT s FROM Student s WHERE s.programFees = :programFees")})
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private Long id;
    
    @Column(name = "REGISTRATION_NUMBER")
    private String registrationNumber;
    
    @Basic(optional = false)
    @Column(name = "ENROLLMENT_DATE")
    @Temporal(TemporalType.DATE)
    private Date enrollmentDate;
    
    @Basic(optional = false)
    @Column(name = "CURRENT_SEMISTER")
    private int currentSemister;
    
    @Basic(optional = false)
    @Column(name = "PROGRAM_FEES")
    private long programFees;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    private List<ChangeRequest> changeRequestList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    private List<Fees> feesList;
    
    @JoinColumn(name = "ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private StudentApplication studentApplication;
    
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne
    private UserAccount userId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    private List<Marks> marksList;

    public Student() {
    }

    public Student(Long id) {
        this.id = id;
    }

    public Student(Long id, Date enrollmentDate, int currentSemister, long programFees) {
        this.id = id;
        this.enrollmentDate = enrollmentDate;
        this.currentSemister = currentSemister;
        this.programFees = programFees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public int getCurrentSemister() {
        return currentSemister;
    }

    public void setCurrentSemister(int currentSemister) {
        this.currentSemister = currentSemister;
    }

    public long getProgramFees() {
        return programFees;
    }

    public void setProgramFees(long programFees) {
        this.programFees = programFees;
    }

    public List<ChangeRequest> getChangeRequestList() {
        return changeRequestList;
    }

    public void setChangeRequestList(List<ChangeRequest> changeRequestList) {
        this.changeRequestList = changeRequestList;
    }

    public List<Fees> getFeesList() {
        return feesList;
    }

    public void setFeesList(List<Fees> feesList) {
        this.feesList = feesList;
    }

    public StudentApplication getStudentApplication() {
        return studentApplication;
    }

    public void setStudentApplication(StudentApplication studentApplication) {
        this.studentApplication = studentApplication;
    }

    public UserAccount getUserId() {
        return userId;
    }

    public void setUserId(UserAccount userId) {
        this.userId = userId;
    }

    public List<Marks> getMarksList() {
        return marksList;
    }

    public void setMarksList(List<Marks> marksList) {
        this.marksList = marksList;
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
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bits.pilani.sem2.dda.assignment1.entity.Student[ id=" + id + " ]";
    }
    
}
