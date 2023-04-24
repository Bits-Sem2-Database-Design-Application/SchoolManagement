package bits.pilani.sem2.dda.assignment1.entity;

import java.io.Serializable;
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

/**
 *
 * @author yogeshk
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Marks.findAll", query = "SELECT m FROM Marks m"),
    @NamedQuery(name = "Marks.findById", query = "SELECT m FROM Marks m WHERE m.id = :id"),
    @NamedQuery(name = "Marks.findByScore", query = "SELECT m FROM Marks m WHERE m.score = :score"),
    @NamedQuery(name = "Marks.findByScoreOutOf", query = "SELECT m FROM Marks m WHERE m.scoreOutOf = :scoreOutOf"),
    @NamedQuery(name = "Marks.findByStatus", query = "SELECT m FROM Marks m WHERE m.status = :status")})
public class Marks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    @Basic(optional = false)
    private int score;
    @Basic(optional = false)
    @Column(name = "SCORE_OUT_OF")
    private int scoreOutOf;
    @Basic(optional = false)
    private String status;
    @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Course courseId;
    @JoinColumn(name = "MARKS_TYPE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private MarksType marksTypeId;
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Student studentId;

    public Marks() {
    }

    public Marks(Long id) {
        this.id = id;
    }

    public Marks(Long id, int score, int scoreOutOf, String status) {
        this.id = id;
        this.score = score;
        this.scoreOutOf = scoreOutOf;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScoreOutOf() {
        return scoreOutOf;
    }

    public void setScoreOutOf(int scoreOutOf) {
        this.scoreOutOf = scoreOutOf;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public MarksType getMarksTypeId() {
        return marksTypeId;
    }

    public void setMarksTypeId(MarksType marksTypeId) {
        this.marksTypeId = marksTypeId;
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
        if (!(object instanceof Marks)) {
            return false;
        }
        Marks other = (Marks) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bits.pilani.sem2.dda.assignment1.entity.Marks[ id=" + id + " ]";
    }
    
}
