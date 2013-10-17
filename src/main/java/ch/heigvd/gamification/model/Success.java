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

/**
 *
 * @author Alexandre Perusset
 */
@Entity
public class Success implements Serializable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="ID")
  private Long id;
  
  private String name;
  
  private String badge;
  
  @ManyToMany(mappedBy="success")
  private List<AppUser> users;
  
  public Success() {
    name = "UNDEF";
    badge = "UNDEF";
  }

  public Success(Success successData) {
    name = successData.name;
    badge = successData.badge;
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