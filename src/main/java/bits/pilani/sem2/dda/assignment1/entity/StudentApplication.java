package bits.pilani.sem2.dda.assignment1.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.NamedStoredProcedureQueries;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.ArrayList;

/**
 *
 * @author yogeshk
 */
@Entity
@Table(name = "STUDENT_APPLICATION")
@NamedQueries({
    @NamedQuery(name = "StudentApplication.findAll", query = "SELECT s FROM StudentApplication s"),
    @NamedQuery(name = "StudentApplication.findById", query = "SELECT s FROM StudentApplication s WHERE s.id = :id"),
    @NamedQuery(name = "StudentApplication.findByFirstName", query = "SELECT s FROM StudentApplication s WHERE s.firstName = :firstName"),
    @NamedQuery(name = "StudentApplication.findByLastName", query = "SELECT s FROM StudentApplication s WHERE s.lastName = :lastName"),
    @NamedQuery(name = "StudentApplication.findByBirthdate", query = "SELECT s FROM StudentApplication s WHERE s.birthdate = :birthdate"),
    @NamedQuery(name = "StudentApplication.findByGender", query = "SELECT s FROM StudentApplication s WHERE s.gender = :gender"),
    @NamedQuery(name = "StudentApplication.findByEmail", query = "SELECT s FROM StudentApplication s WHERE s.email = :email"),
    @NamedQuery(name = "StudentApplication.findByApplicateDate", query = "SELECT s FROM StudentApplication s WHERE s.applicationDate = :applicateDate"),
    @NamedQuery(name = "StudentApplication.findByStatus", query = "SELECT s FROM StudentApplication s WHERE s.status = :status")
})
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "generate_otp",
        procedureName = "generate_otp",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "length", type = Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.OUT, name = "otp", type = Integer.class)
        })
})
public class StudentApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;

    @Basic(optional = false)
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Basic(optional = false)
    @Column(name = "LAST_NAME")
    private String lastName;

    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @Basic(optional = false)
    private String gender;

    @Basic(optional = false)
    private String email;

    @Basic(optional = false)
    @Column(name = "APPLICATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date applicationDate;

    @Basic(optional = false)
    @Lob
    @Column(name = "SUPPORTING_DOCS_AS_SINGLE_PDF")
    private byte[] supportingDocsAsSinglePdf;

    private String status;

    private String comments;

    private Integer otp;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "studentApplication")
    private Student student;

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "studentApplication")
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "studentApplication")
    private List<StudentAddress> studentAddressList;

    @JoinColumn(name = "PROGRAM_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Program programId;

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "studentApplication")
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "studentApplication")
    private List<StudentContact> studentContactList;

    public StudentApplication() {
        studentAddressList = new ArrayList<>();
        studentContactList = new ArrayList<>();
    }

    public StudentApplication(Long id) {
        this.id = id;
    }

    public StudentApplication(Long id, String firstName, String lastName,
        Date birthdate, String gender, String email, Date applicateDate,
        byte[] supportingDocsAsSinglePdf) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.email = email;
        this.applicationDate = applicateDate;
        this.supportingDocsAsSinglePdf = supportingDocsAsSinglePdf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicateDate) {
        this.applicationDate = applicateDate;
    }

    public byte[] getSupportingDocsAsSinglePdf() {
        return supportingDocsAsSinglePdf;
    }

    public void setSupportingDocsAsSinglePdf(byte[] supportingDocsAsSinglePdf) {
        this.supportingDocsAsSinglePdf = supportingDocsAsSinglePdf;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return the otp
     */
    public Integer getOtp() {
        return otp;
    }

    /**
     * @param otp the otp to set
     */
    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<StudentAddress> getStudentAddressList() {
        return studentAddressList;
    }

    public void setStudentAddressList(List<StudentAddress> studentAddressList) {
        this.studentAddressList = studentAddressList;
    }

    public Program getProgramId() {
        return programId;
    }

    public void setProgramId(Program programId) {
        this.programId = programId;
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
        if (!(object instanceof StudentApplication)) {
            return false;
        }
        StudentApplication other = (StudentApplication) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bits.pilani.sem2.dda.assignment1.entity.StudentApplication[ id=" + id + " ]";
    }

}
