package ch.heigvd.gamification.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String title;
    private int points;
    private String description;
    
    public ActionType() {
        title = "UNDEF";
        points = -1;
        description = "UNDEF";
    }
    
    public ActionType(ActionType actionTypeData) {
        this.title = actionTypeData.title;
        this.points = actionTypeData.points;
        this.description = actionTypeData.description;
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
    
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActionType)) {
            return false;
        }
        ActionType other = (ActionType)object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }
    
    @Override
    public String toString() {
        return "ch.heigvd.gamification.model.ActionType[ id=" + id + " ]";
    }
}
