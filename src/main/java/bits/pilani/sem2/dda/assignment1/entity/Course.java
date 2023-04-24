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

/**
 *
 * @author yogeshk
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c"),
    @NamedQuery(name = "Course.findById", query = "SELECT c FROM Course c WHERE c.id = :id"),
    @NamedQuery(name = "Course.findByName", query = "SELECT c FROM Course c WHERE c.name = :name"),
    @NamedQuery(name = "Course.findByCode", query = "SELECT c FROM Course c WHERE c.code = :code"),
    @NamedQuery(name = "Course.findByCreditUnit", query = "SELECT c FROM Course c WHERE c.creditUnit = :creditUnit")})
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    @Basic(optional = false)
    private String name;
    @Basic(optional = false)
    private String code;
    @Basic(optional = false)
    @Column(name = "CREDIT_UNIT")
    private int creditUnit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<ExamFees> examFeesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<ProgramSemesterFees> programSemesterFeesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<ProgramCourse> programCourseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private List<Marks> marksList;

    public Course() {
    }

    public Course(Long id) {
        this.id = id;
    }

    public Course(Long id, String name, String code, int creditUnit) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.creditUnit = creditUnit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCreditUnit() {
        return creditUnit;
    }

    public void setCreditUnit(int creditUnit) {
        this.creditUnit = creditUnit;
    }

    public List<ExamFees> getExamFeesList() {
        return examFeesList;
    }

    public void setExamFeesList(List<ExamFees> examFeesList) {
        this.examFeesList = examFeesList;
    }

    public List<ProgramSemesterFees> getProgramSemesterFeesList() {
        return programSemesterFeesList;
    }

    public void setProgramSemesterFeesList(List<ProgramSemesterFees> programSemesterFeesList) {
        this.programSemesterFeesList = programSemesterFeesList;
    }

    public List<ProgramCourse> getProgramCourseList() {
        return programCourseList;
    }

    public void setProgramCourseList(List<ProgramCourse> programCourseList) {
        this.programCourseList = programCourseList;
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
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bits.pilani.sem2.dda.assignment1.entity.Course[ id=" + id + " ]";
    }
    
}
