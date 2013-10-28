package ch.heigvd.gamification.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author Alexandre Perusset
 */
@Entity
public class UserAction implements Serializable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="ID")
  private Long id;
  
  private String title;
  
  private String description;
  
  private Integer points;
  
  //Load only on demand, is not the owner, so if we had a user to the list, it
  //will not be registered into association table between UserAction and AppUser
  //In good use, we have to add both AppUser and UserAction to respective Lists
  @ManyToMany(mappedBy="actions", fetch = FetchType.LAZY)
  private final List<AppUser> users;
  
  //TODO actiontype

  public UserAction() {
    title = "UNDEF";
    description = "UNDEF";
    points = 0;
    users = new LinkedList<>();
  }
  
  public UserAction(UserAction userActionData) {
    title = userActionData.title;
    description = userActionData.description;
    points = userActionData.points;
    users = userActionData.users;
  }
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  
  public String getTitle() {
    return title;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public String getDescription() {
    return description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public Integer getPoints() {
    return points;
  }
  
  public void setPoint(Integer points) {
    this.points = points;
  }
  
  public List<AppUser> getUsers() {
    return users;
  }
  
  public void addUser(AppUser user) {
    users.add(user);
  }
  
  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof UserAction)) {
      return false;
    }
    UserAction other = (UserAction)object;
    return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
  }

  @Override
  public String toString() {
    return "ch.heigvd.gamification.model.UserAction[ id=" + id + " ]";
  }
}
