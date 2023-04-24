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
public class ExamFeesPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "FEES_ID")
    private long feesId;
    @Basic(optional = false)
    @Column(name = "COURSE_ID")
    private long courseId;

    public ExamFeesPK() {
    }

    public ExamFeesPK(long feesId, long courseId) {
        this.feesId = feesId;
        this.courseId = courseId;
    }

    public long getFeesId() {
        return feesId;
    }

    public void setFeesId(long feesId) {
        this.feesId = feesId;
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
        hash += (int) feesId;
        hash += (int) courseId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamFeesPK)) {
            return false;
        }
        ExamFeesPK other = (ExamFeesPK) object;
        if (this.feesId != other.feesId) {
            return false;
        }
        if (this.courseId != other.courseId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bits.pilani.sem2.dda.assignment1.entity.ExamFeesPK[ feesId=" + feesId + ", courseId=" + courseId + " ]";
    }
    
}
