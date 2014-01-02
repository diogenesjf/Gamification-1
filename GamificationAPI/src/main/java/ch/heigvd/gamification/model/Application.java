package ch.heigvd.gamification.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * This class represent an application.
 * 
 * @author Thomas Moegli
 */
@NamedQueries({
  @NamedQuery(
          name = "findAllApplication",
          query = "select a from Application a"
  )
})
@Entity
public class Application implements Serializable {

  @Id                                               //ID field
  @GeneratedValue(strategy = GenerationType.AUTO)   //Auto-increment
  private Long id;

  private String name;

  //Load the objects only on demand
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "application")
  private final List<Rule> rules;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "application")
  private final List<Success> successes;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "application")
  private final List<AppUser> users;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "application")
  private final List<AppAction> actionsTypes;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "application")
  private final List<Event> events;

  public Application() {
    name = "UNDEF";
    rules = new LinkedList<>();
    successes = new LinkedList<>();
    users = new LinkedList<>();
    actionsTypes = new LinkedList<>();
    events = new LinkedList<>();
  }

  public Application(Application app) {
    name = app.name;
    rules = app.rules;
    successes = app.successes;
    users = app.users;
    actionsTypes = app.actionsTypes;
    events = app.events;
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

  public void addRule(Rule rule) {
    rules.add(rule);
  }

  public List<Rule> getRules() {
    return rules;
  }

  public void addSuccess(Success success) {
    successes.add(success);
  }

  public List<Success> getSuccesses() {
    return successes;
  }

  public void addEvent(Event event) {
    events.add(event);
  }

  public List<Event> getEvents() {
    return events;
  }

  public void addActionType(AppAction actionType) {
    actionsTypes.add(actionType);
  }

  public List<AppAction> getActionsTypes() {
    return actionsTypes;
  }

  public void addUser(AppUser user) {
    users.add(user);
  }

  public List<AppUser> getUsers() {
    return users;
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Application) || this.id == null) {
      return false;
    }
    Application other = (Application)object;
    return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));

  }

  @Override
  public String toString() {
    return "ch.heigvd.gamification.model.Application[ id=" + id + " ]";
  }
}
