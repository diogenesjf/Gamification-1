package ch.heigvd.gamification.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Gaël Jobin
 */
@NamedQueries({
        @NamedQuery(
                name = "findAllRules",
                query = "select u from Rule u"
        )
})
@Entity
public class Rule implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    private String description;
    private int acquiredPoints;
    
    @ManyToOne
    private ActionType actionType;
    
    public Rule() {
        name = "UNDEF";
        description = "UNDEF";
        acquiredPoints = -1;
        actionType = null;
    }
    
    public Rule(Rule ruleData) {
        this.name = ruleData.name;
        this.description = ruleData.description;
        this.acquiredPoints = ruleData.acquiredPoints;
        this.actionType = ruleData.actionType;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public int getAcquiredPoints() {
        return this.acquiredPoints;
    }
    
    public void setAcquiredPoints(int acquiredPoints)
    {
        this.acquiredPoints = acquiredPoints;
    }
    
    public ActionType getActionType() {
        return this.actionType;
    }
    
    public void setActionType(ActionType newActionType)
    {
        this.actionType = newActionType;
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
        if (!(object instanceof Rule)) {
            return false;
        }
        Rule other = (Rule) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "ch.heigvd.gamification.model.Rule[ id=" + id + " ]";
    }
}
