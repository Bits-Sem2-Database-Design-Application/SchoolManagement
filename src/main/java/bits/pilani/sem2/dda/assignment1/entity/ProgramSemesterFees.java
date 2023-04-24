package bits.pilani.sem2.dda.assignment1.entity;

import java.io.Serializable;
import jakarta.persistence.Basic;
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
@Table(name = "PROGRAM_SEMESTER_FEES")
@NamedQueries({
    @NamedQuery(name = "ProgramSemesterFees.findAll", query = "SELECT p FROM ProgramSemesterFees p"),
    @NamedQuery(name = "ProgramSemesterFees.findByProgramId", query = "SELECT p FROM ProgramSemesterFees p WHERE p.programSemesterFeesPK.programId = :programId"),
    @NamedQuery(name = "ProgramSemesterFees.findBySemester", query = "SELECT p FROM ProgramSemesterFees p WHERE p.semester = :semester"),
    @NamedQuery(name = "ProgramSemesterFees.findByFees", query = "SELECT p FROM ProgramSemesterFees p WHERE p.fees = :fees"),
    @NamedQuery(name = "ProgramSemesterFees.findByCourseId", query = "SELECT p FROM ProgramSemesterFees p WHERE p.programSemesterFeesPK.courseId = :courseId")})
public class ProgramSemesterFees implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProgramSemesterFeesPK programSemesterFeesPK;
    @Basic(optional = false)
    private int semester;
    @Basic(optional = false)
    private long fees;
    @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Course course;
    @JoinColumn(name = "PROGRAM_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Program program;

    public ProgramSemesterFees() {
    }

    public ProgramSemesterFees(ProgramSemesterFeesPK programSemesterFeesPK) {
        this.programSemesterFeesPK = programSemesterFeesPK;
    }

    public ProgramSemesterFees(ProgramSemesterFeesPK programSemesterFeesPK, int semester, long fees) {
        this.programSemesterFeesPK = programSemesterFeesPK;
        this.semester = semester;
        this.fees = fees;
    }

    public ProgramSemesterFees(long programId, long courseId) {
        this.programSemesterFeesPK = new ProgramSemesterFeesPK(programId, courseId);
    }

    public ProgramSemesterFeesPK getProgramSemesterFeesPK() {
        return programSemesterFeesPK;
    }

    public void setProgramSemesterFeesPK(ProgramSemesterFeesPK programSemesterFeesPK) {
        this.programSemesterFeesPK = programSemesterFeesPK;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public long getFees() {
        return fees;
    }

    public void setFees(long fees) {
        this.fees = fees;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (programSemesterFeesPK != null ? programSemesterFeesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramSemesterFees)) {
            return false;
        }
        ProgramSemesterFees other = (ProgramSemesterFees) object;
        if ((this.programSemesterFeesPK == null && other.programSemesterFeesPK != null) || (this.programSemesterFeesPK != null && !this.programSemesterFeesPK.equals(other.programSemesterFeesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bits.pilani.sem2.dda.assignment1.entity.ProgramSemesterFees[ programSemesterFeesPK=" + programSemesterFeesPK + " ]";
    }
    
}
