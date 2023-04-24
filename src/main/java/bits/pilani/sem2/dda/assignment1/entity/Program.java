package bits.pilani.sem2.dda.assignment1.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
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
    @NamedQuery(name = "Program.findAll", query = "SELECT p FROM Program p"),
    @NamedQuery(name = "Program.findById", query = "SELECT p FROM Program p WHERE p.id = :id"),
    @NamedQuery(name = "Program.findByName", query = "SELECT p FROM Program p WHERE p.name = :name"),
    @NamedQuery(name = "Program.findByAbbreviation", query = "SELECT p FROM Program p WHERE p.abbreviation = :abbreviation"),
    @NamedQuery(name = "Program.findByFees", query = "SELECT p FROM Program p WHERE p.fees = :fees")})
public class Program implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    @Basic(optional = false)
    private String name;
    @Basic(optional = false)
    private String abbreviation;
    @Basic(optional = false)
    private long fees;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "program")
    private List<ProgramSemesterFees> programSemesterFeesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programId")
    private List<StudentApplication> studentApplicationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "program")
    private List<ProgramCourse> programCourseList;

    public Program() {
    }

    public Program(Long id) {
        this.id = id;
    }

    public Program(Long id, String name, String abbreviation, long fees) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
        this.fees = fees;
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

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public long getFees() {
        return fees;
    }

    public void setFees(long fees) {
        this.fees = fees;
    }

    public List<ProgramSemesterFees> getProgramSemesterFeesList() {
        return programSemesterFeesList;
    }

    public void setProgramSemesterFeesList(List<ProgramSemesterFees> programSemesterFeesList) {
        this.programSemesterFeesList = programSemesterFeesList;
    }

    public List<StudentApplication> getStudentApplicationList() {
        return studentApplicationList;
    }

    public void setStudentApplicationList(List<StudentApplication> studentApplicationList) {
        this.studentApplicationList = studentApplicationList;
    }

    public List<ProgramCourse> getProgramCourseList() {
        return programCourseList;
    }

    public void setProgramCourseList(List<ProgramCourse> programCourseList) {
        this.programCourseList = programCourseList;
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
        if (!(object instanceof Program)) {
            return false;
        }
        Program other = (Program) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bits.pilani.sem2.dda.assignment1.entity.Program[ id=" + id + " ]";
    }
    
}
