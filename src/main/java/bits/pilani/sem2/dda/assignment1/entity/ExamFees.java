package bits.pilani.sem2.dda.assignment1.entity;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
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
@Table(name = "EXAM_FEES")
@NamedQueries({
    @NamedQuery(name = "ExamFees.findAll", query = "SELECT e FROM ExamFees e"),
    @NamedQuery(name = "ExamFees.findByFeesId", query = "SELECT e FROM ExamFees e WHERE e.examFeesPK.feesId = :feesId"),
    @NamedQuery(name = "ExamFees.findByCourseId", query = "SELECT e FROM ExamFees e WHERE e.examFeesPK.courseId = :courseId"),
    @NamedQuery(name = "ExamFees.findByExamFeesAmount", query = "SELECT e FROM ExamFees e WHERE e.examFeesAmount = :examFeesAmount")})
public class ExamFees implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ExamFeesPK examFeesPK;
    @Basic(optional = false)
    @Column(name = "EXAM_FEES_AMOUNT")
    private long examFeesAmount;
    @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Course course;
    @JoinColumn(name = "FEES_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Fees fees;

    public ExamFees() {
    }

    public ExamFees(ExamFeesPK examFeesPK) {
        this.examFeesPK = examFeesPK;
    }

    public ExamFees(ExamFeesPK examFeesPK, long examFeesAmount) {
        this.examFeesPK = examFeesPK;
        this.examFeesAmount = examFeesAmount;
    }

    public ExamFees(long feesId, long courseId) {
        this.examFeesPK = new ExamFeesPK(feesId, courseId);
    }

    public ExamFeesPK getExamFeesPK() {
        return examFeesPK;
    }

    public void setExamFeesPK(ExamFeesPK examFeesPK) {
        this.examFeesPK = examFeesPK;
    }

    public long getExamFeesAmount() {
        return examFeesAmount;
    }

    public void setExamFeesAmount(long examFeesAmount) {
        this.examFeesAmount = examFeesAmount;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Fees getFees() {
        return fees;
    }

    public void setFees(Fees fees) {
        this.fees = fees;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (examFeesPK != null ? examFeesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamFees)) {
            return false;
        }
        ExamFees other = (ExamFees) object;
        if ((this.examFeesPK == null && other.examFeesPK != null) || (this.examFeesPK != null && !this.examFeesPK.equals(other.examFeesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bits.pilani.sem2.dda.assignment1.entity.ExamFees[ examFeesPK=" + examFeesPK + " ]";
    }
    
}
