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
@Table(name = "MARKS_TYPE")
@NamedQueries({
    @NamedQuery(name = "MarksType.findAll", query = "SELECT m FROM MarksType m"),
    @NamedQuery(name = "MarksType.findById", query = "SELECT m FROM MarksType m WHERE m.id = :id"),
    @NamedQuery(name = "MarksType.findByName", query = "SELECT m FROM MarksType m WHERE m.name = :name")})
public class MarksType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    @Basic(optional = false)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "marksTypeId")
    private List<Marks> marksList;

    public MarksType() {
    }

    public MarksType(Long id) {
        this.id = id;
    }

    public MarksType(Long id, String name) {
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
        if (!(object instanceof MarksType)) {
            return false;
        }
        MarksType other = (MarksType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bits.pilani.sem2.dda.assignment1.entity.MarksType[ id=" + id + " ]";
    }
    
}
