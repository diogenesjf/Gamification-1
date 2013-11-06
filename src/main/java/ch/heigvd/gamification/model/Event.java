package ch.heigvd.gamification.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Alexandre Perusset
 */
@NamedQueries({
        @NamedQuery(
                name = "findAllEvents",
                query = "select e from Event e"
        )
})

@Entity
public class Event implements Serializable {
  
  @Id                                               //ID field
  @GeneratedValue(strategy = GenerationType.AUTO)   //Auto-increment
  private Long id;
  
  @ManyToOne(fetch=FetchType.EAGER)
  private AppUser user;
  
  @ManyToOne(fetch=FetchType.EAGER)
  private ActionType actionType;
  
  private long eventtimestamp;
  
  public Event() {
    user = null;
    actionType = null;
    eventtimestamp = -1;
  }
  
  public Event(Event event) {
    user = event.user;
    actionType = event.actionType;
    eventtimestamp = event.eventtimestamp;
  }
  
  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public AppUser getUser() {
    return this.user;
  }
  
  public void setUser(AppUser user) {
    this.user = user;
  }
  
  public ActionType getActionType() {
    return this.actionType;
  }
  
  public void setActionType(ActionType actionType) {
    this.actionType = actionType;
  }
  
  public long getTimestamp() {
    return eventtimestamp;
  }
  
  public void setTimestamp(long timestamp) {
    this.eventtimestamp = timestamp;
  }
  
  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Event)) {
      return false;
    }
    Event other = (Event)object;
    return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
  }

  @Override
  public String toString() {
    return "ch.heigvd.gamification.model.Event[ id=" + id + " ]";
  }
}
