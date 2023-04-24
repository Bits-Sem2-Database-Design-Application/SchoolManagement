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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 *
 * @author yogeshk
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Fees.findAll", query = "SELECT f FROM Fees f"),
    @NamedQuery(name = "Fees.findById", query = "SELECT f FROM Fees f WHERE f.id = :id"),
    @NamedQuery(name = "Fees.findByTransactionDate", query = "SELECT f FROM Fees f WHERE f.transactionDate = :transactionDate"),
    @NamedQuery(name = "Fees.findByMode", query = "SELECT f FROM Fees f WHERE f.mode = :mode"),
    @NamedQuery(name = "Fees.findByReferenceNumber", query = "SELECT f FROM Fees f WHERE f.referenceNumber = :referenceNumber"),
    @NamedQuery(name = "Fees.findByTransactionAmount", query = "SELECT f FROM Fees f WHERE f.transactionAmount = :transactionAmount"),
    @NamedQuery(name = "Fees.findBySemester", query = "SELECT f FROM Fees f WHERE f.semester = :semester")})
public class Fees implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "TRANSACTION_DATE")
    @Temporal(TemporalType.DATE)
    private Date transactionDate;

    @Basic(optional = false)
    private String bank;
    
    @Basic(optional = false)
    private String mode;
    @Basic(optional = false)
    @Column(name = "REFERENCE_NUMBER")
    private String referenceNumber;
    @Basic(optional = false)
    @Column(name = "TRANSACTION_AMOUNT")
    private long transactionAmount;
    @Basic(optional = false)
    private int semester;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fees")
    private List<ExamFees> examFeesList;
    @JoinColumn(name = "FEES_TYPE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private FeesType feesTypeId;
    
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Student studentId;

    public Fees() {
    }

    public Fees(Long id) {
        this.id = id;
    }

    public Fees(Long id, Date transactionDate, String mode, String referenceNumber, long transactionAmount, int semester) {
        this.id = id;
        this.transactionDate = transactionDate;
        this.mode = mode;
        this.referenceNumber = referenceNumber;
        this.transactionAmount = transactionAmount;
        this.semester = semester;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * @return the bank
     */
    public String getBank() {
        return bank;
    }

    /**
     * @param bank the bank to set
     */
    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public long getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(long transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public List<ExamFees> getExamFeesList() {
        return examFeesList;
    }

    public void setExamFeesList(List<ExamFees> examFeesList) {
        this.examFeesList = examFeesList;
    }

    public FeesType getFeesTypeId() {
        return feesTypeId;
    }

    public void setFeesTypeId(FeesType feesTypeId) {
        this.feesTypeId = feesTypeId;
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
        if (!(object instanceof Fees)) {
            return false;
        }
        Fees other = (Fees) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bits.pilani.sem2.dda.assignment1.entity.Fees[ id=" + id + " ]";
    }
    
}
