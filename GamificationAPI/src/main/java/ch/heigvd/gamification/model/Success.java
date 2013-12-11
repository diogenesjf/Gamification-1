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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Alexandre Perusset
 */
@NamedQueries({
  @NamedQuery(
          name = "findAllSuccess",
          query = "select s from Success s where s.application.id = :appid"
  )
})
@Entity
public class Success implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  private String badge;

  @ManyToMany(fetch = FetchType.LAZY)
  private final List<Rule> rules;

  @ManyToOne
  private Application application;

  public Success() {
    name = "UNDEF";
    badge = "UNDEF";
    rules = new LinkedList<>();
  }

  public Success(Success success) {
    name = success.name;
    badge = success.badge;
    rules = success.rules;
    application = success.application;
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

  public String getBadge() {
    return badge;
  }

  public void setBadge(String badge) {
    this.badge = badge;
  }

  public List<Rule> getRules() {
    return rules;
  }

  public void addRule(Rule rule) {
    rules.add(rule);
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
    if (!(object instanceof Success) || this.id == -1) {
      return false;
    }
    Success other = (Success) object;
    return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
  }

  @Override
  public String toString() {
    return "ch.heigvd.gamification.model.Success[id=" + id + "]";
  }
}
