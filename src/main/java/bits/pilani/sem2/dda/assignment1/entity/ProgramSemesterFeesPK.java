package bits.pilani.sem2.dda.assignment1.entity;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 *
 * @author yogeshk
 */
@Embeddable
public class ProgramSemesterFeesPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "PROGRAM_ID")
    private long programId;
    @Basic(optional = false)
    @Column(name = "COURSE_ID")
    private long courseId;

    public ProgramSemesterFeesPK() {
    }

    public ProgramSemesterFeesPK(long programId, long courseId) {
        this.programId = programId;
        this.courseId = courseId;
    }

    public long getProgramId() {
        return programId;
    }

    public void setProgramId(long programId) {
        this.programId = programId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) programId;
        hash += (int) courseId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramSemesterFeesPK)) {
            return false;
        }
        ProgramSemesterFeesPK other = (ProgramSemesterFeesPK) object;
        if (this.programId != other.programId) {
            return false;
        }
        if (this.courseId != other.courseId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bits.pilani.sem2.dda.assignment1.entity.ProgramSemesterFeesPK[ programId=" + programId + ", courseId=" + courseId + " ]";
    }
    
}
