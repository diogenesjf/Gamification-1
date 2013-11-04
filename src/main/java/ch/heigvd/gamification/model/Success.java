package ch.heigvd.gamification.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
 * @author Alexandre Perusset
 */
@NamedQueries({
        @NamedQuery(
                name = "findAllSuccess",
                query = "select u from Success u"
        )
})
@Entity
public class Success implements Serializable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="ID")
  private Long id;
  
  private String name;
  
  private String badge;
  
  @ManyToMany(mappedBy="successes", fetch = FetchType.LAZY)
  private final List<Rule> rules;
  
  @ManyToMany(mappedBy="successes", fetch = FetchType.LAZY)
  private final List<AppUser> users;
  
  public Success() {
    name = "UNDEF";
    badge = "UNDEF";
    users = new LinkedList<>();
    rules = new LinkedList<>();
  }

  public Success(Success successData) {
    name = successData.name;
    badge = successData.badge;
    users = successData.users;
    rules = successData.rules;
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
  
  public List<AppUser> getUsers() {
    return users;
  }
  
  public List<Rule> getRules() {
    return rules;
  }
  
  public void addRule(Rule rule) {
    rules.add(rule);
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
    if (!(object instanceof Success)) {
      return false;
    }
    Success other = (Success)object;
    return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
  }

  @Override
  public String toString() {
    return "ch.heigvd.gamification.model.Success[ id=" + id + " ]";
  }
}
