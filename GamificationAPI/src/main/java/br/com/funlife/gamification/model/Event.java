package br.com.funlife.gamification.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * This class represent an event. An event occurend when a user do an action.
 *
 * @author Diogenes Firmiano
 */
@NamedQueries({
  @NamedQuery(
          name = "findAllEvents",
          query = "select e from Event e "
          + "where e.application.id = :appid "
          + "order by e.evenTimestamp desc"
  )
})

@Entity
public class Event implements Serializable {

  @Id                                               //ID field
  @GeneratedValue(strategy = GenerationType.AUTO)   //Auto-increment
  private Long id;

  @ManyToOne
  private AppUser user;

  @ManyToOne
  private AppAction action;

  private long evenTimestamp;

  @ManyToOne
  private Application application;

  public Event() {
    user = null;
    action = null;
    evenTimestamp = -1;
  }

  public Event(Event event) {
    user = event.user;
    action = event.action;
    evenTimestamp = event.evenTimestamp;
    application = event.application;
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

  public AppAction getActionType() {
    return this.action;
  }

  public void setActionType(AppAction actionType) {
    this.action = actionType;
  }

  public long getTimestamp() {
    return evenTimestamp;
  }

  public void setTimestamp(long timestamp) {
    this.evenTimestamp = timestamp;
  }

  public Application getApplication() {
    return this.application;
  }

  public void setApplication(Application application) {
    this.application = application;
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Event) || this.id == null) {
      return false;
    }
    Event other = (Event) object;
    return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
  }

  @Override
  public String toString() {
    return "br.com.funlife.gamification.model.Event[id=" + id + "]";
  }
}
