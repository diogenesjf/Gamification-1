package ch.heigvd.gamification.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Gaël Jobin
 */
@NamedQueries({
        @NamedQuery(
                name = "findAllActionTypes",
                query = "select e from ActionType e"
        )
})
@Entity
public class ActionType implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String title;
    private int points;
    private String description;
    
    //Load success only on demand
    @ManyToMany(fetch = FetchType.LAZY)
    private final List<Rule> rules;
    
    public ActionType() {
        title = "UNDEF";
        points = -1;
        description = "UNDEF";
        rules = new LinkedList<>();
    }
    
    public ActionType(ActionType actionTypeData) {
        this.title = actionTypeData.title;
        this.points = actionTypeData.points;
        this.description = actionTypeData.description;
        this.rules = actionTypeData.rules;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getPoints(){
        return this.points;
    }
    
    public void setPoints(int points) {
        this.points = points;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<Rule> getRules() {
        return rules;
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
        if (!(object instanceof ActionType)) {
            return false;
        }
        ActionType other = (ActionType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "ch.heigvd.gamification.model.ActionType[ id=" + id + " ]";
    }
}
