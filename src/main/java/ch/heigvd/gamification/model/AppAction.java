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
 * This class is an Action. An action can be performed by a user. When a user
 * perform an action, this is called an Event.
 *
 * @author Gaël Jobin
 */
@NamedQueries({
  @NamedQuery(
          name = "findAllAppActions",
          query = "select a from AppAction a where a.application.id = :appid"
  )
})
@Entity
public class AppAction implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String title;

  private int points;

  private String description;

  @ManyToOne
  private Application application;

  public AppAction() {
    title = "UNDEF";
    points = -1;
    description = "UNDEF";
  }

  public AppAction(AppAction action) {
    this.title = action.title;
    this.points = action.points;
    this.description = action.description;
    this.application = action.application;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getPoints() {
    return this.points;
  }

  public void setPoints(int points) {
    this.points = points;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
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
    if (!(object instanceof AppAction) || this.id == -1) {
      return false;
    }
    AppAction other = (AppAction) object;
    return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
  }

  @Override
  public String toString() {
    return "ch.heigvd.gamification.model.AppAction[id=" + id + "]";
  }
}
