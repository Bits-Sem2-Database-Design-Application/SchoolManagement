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
@Table(name = "PROGRAM_COURSE")
@NamedQueries({
    @NamedQuery(name = "ProgramCourse.findAll", query = "SELECT p FROM ProgramCourse p"),
    @NamedQuery(name = "ProgramCourse.findByProgramId", query = "SELECT p FROM ProgramCourse p WHERE p.programCoursePK.programId = :programId"),
    @NamedQuery(name = "ProgramCourse.findByCourseId", query = "SELECT p FROM ProgramCourse p WHERE p.programCoursePK.courseId = :courseId"),
    @NamedQuery(name = "ProgramCourse.findBySemester", query = "SELECT p FROM ProgramCourse p WHERE p.semester = :semester")})
public class ProgramCourse implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProgramCoursePK programCoursePK;
    @Basic(optional = false)
    private int semester;
    @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Course course;
    @JoinColumn(name = "PROGRAM_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Program program;

    public ProgramCourse() {
    }

    public ProgramCourse(ProgramCoursePK programCoursePK) {
        this.programCoursePK = programCoursePK;
    }

    public ProgramCourse(ProgramCoursePK programCoursePK, int semester) {
        this.programCoursePK = programCoursePK;
        this.semester = semester;
    }

    public ProgramCourse(long programId, long courseId) {
        this.programCoursePK = new ProgramCoursePK(programId, courseId);
    }

    public ProgramCoursePK getProgramCoursePK() {
        return programCoursePK;
    }

    public void setProgramCoursePK(ProgramCoursePK programCoursePK) {
        this.programCoursePK = programCoursePK;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
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
        hash += (programCoursePK != null ? programCoursePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramCourse)) {
            return false;
        }
        ProgramCourse other = (ProgramCourse) object;
        if ((this.programCoursePK == null && other.programCoursePK != null) || (this.programCoursePK != null && !this.programCoursePK.equals(other.programCoursePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bits.pilani.sem2.dda.assignment1.entity.ProgramCourse[ programCoursePK=" + programCoursePK + " ]";
    }
    
}
