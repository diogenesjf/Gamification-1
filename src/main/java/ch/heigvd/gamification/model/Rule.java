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
          query = "select r from Rule r"
  )
})
@Entity
public class Rule implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;
  
  private String description;
  
  private int acquiredPoints;

  @ManyToOne
  private AppAction action;
  
  @ManyToOne
  private Application application;

  public Rule() {
    name = "UNDEF";
    description = "UNDEF";
    acquiredPoints = -1;
    action = null;
  }

  public Rule(Rule rule) {
    this.name = rule.name;
    this.description = rule.description;
    this.acquiredPoints = rule.acquiredPoints;
    this.action = rule.action;
    this.application = rule.application;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getAcquiredPoints() {
    return this.acquiredPoints;
  }

  public void setAcquiredPoints(int acquiredPoints) {
    this.acquiredPoints = acquiredPoints;
  }

  public AppAction getAction() {
    return this.action;
  }

  public void setAction(AppAction action) {
    this.action = action;
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
    if (!(object instanceof Rule) || this.id == -1) {
      return false;
    }
    Rule other = (Rule)object;
    return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
  }

  @Override
  public String toString() {
    return "ch.heigvd.gamification.model.Rule[id=" + id + "]";
  }
}
