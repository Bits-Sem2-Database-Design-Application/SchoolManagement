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
import jakarta.persistence.Table;

/**
 *
 * @author yogeshk
 */
@Entity
@Table(name = "FEES_TYPE")
@NamedQueries({
    @NamedQuery(name = "FeesType.findAll", query = "SELECT f FROM FeesType f"),
    @NamedQuery(name = "FeesType.findById", query = "SELECT f FROM FeesType f WHERE f.id = :id"),
    @NamedQuery(name = "FeesType.findByName", query = "SELECT f FROM FeesType f WHERE f.name = :name")})
public class FeesType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    @Basic(optional = false)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "feesTypeId")
    private List<Fees> feesList;

    public FeesType() {
    }

    public FeesType(Long id) {
        this.id = id;
    }

    public FeesType(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public List<Fees> getFeesList() {
        return feesList;
    }

    public void setFeesList(List<Fees> feesList) {
        this.feesList = feesList;
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
        if (!(object instanceof FeesType)) {
            return false;
        }
        FeesType other = (FeesType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bits.pilani.sem2.dda.assignment1.entity.FeesType[ id=" + id + " ]";
    }
    
}
