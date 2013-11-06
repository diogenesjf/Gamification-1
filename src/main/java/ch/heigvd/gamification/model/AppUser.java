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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Alexandre Perusset
 */
@NamedQueries({
        @NamedQuery(
                name = "findAllUsers",
                query = "select u from AppUser u"
        )
})

@Entity
public class AppUser implements Serializable {

  @Id                                               //ID field
  @GeneratedValue(strategy = GenerationType.AUTO)   //Auto-increment
  private Long id;

  private String name;

  private String surname;

  //If we don't want a nullable field
  @Column(nullable=false)
  private String nickname;

  private String password;
  
  //Load success only on demand
  @ManyToMany(fetch = FetchType.LAZY)
  private final List<Success> successes;
  
  //Load events only on demand
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  private final List<Event> events;

  public AppUser() {
    name = "UNDEF";
    surname = "UNDEF";
    nickname = "UNDEF";
    password = "UNDEF";
    successes = new LinkedList<>();
    events = new LinkedList<>();
  }

  public AppUser(AppUser userData) {
    name = userData.name;
    surname = userData.surname;
    nickname = userData.nickname;
    password = userData.password;
    successes = userData.successes;
    events = userData.events;
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

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Success> getSuccesses() {
    return successes;
  }
  
  public List<Event> getEvents() {
    return events;
  }
  
  public void addEvent(Event event) {
    events.add(event);
  }
  
  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof AppUser)) {
      return false;
    }
    AppUser other = (AppUser)object;
    return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
  }

  @Override
  public String toString() {
    return "ch.heigvd.gamification.model.AppUser[ id=" + id + " ]";
  }

}
