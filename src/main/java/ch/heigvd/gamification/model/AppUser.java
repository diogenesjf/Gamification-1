package ch.heigvd.gamification.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Alexandre Perusset
 */
@NamedQueries({
        @NamedQuery(
                name = "findAllUsers",
                query = "select u from AppUser u"
        ),
        //Prepared Statements ?
        @NamedQuery( //TODO paramètrage ?
                name = "findAllUserSuccess",
                query = "select s from Success s"
        ),
        @NamedQuery( //TODO paramètrage ?
                name = "findAllUserActions",
                query = "select s from Success s"
        )
})

@Entity
public class AppUser implements Serializable {

  @Id                                               //ID field
  @GeneratedValue(strategy = GenerationType.AUTO)   //Auto-increment
  @Column(name="ID")                                //Column name
  private Long id;

  private String name;

  private String surname;

  private String nickname;

  private String password;
  
  //TODO qui est coneptuellement le owner ?
  @ManyToMany
  private List<Success> success;
  
  //TODO qui est conceptuellement le owner ?
  @ManyToMany
  private List<UserAction> actions;

  public AppUser() {
    name = "UNDEF";
    surname = "UNDEF";
    nickname = "UNDEF";
    password = "UNDEF";
  }

  public AppUser(AppUser userData) {
    name = userData.name;
    surname = userData.surname;
    nickname = userData.nickname;
    password = userData.password;
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
    return "ch.heigvd.gamification.model.User[ id=" + id + " ]";
  }

}
