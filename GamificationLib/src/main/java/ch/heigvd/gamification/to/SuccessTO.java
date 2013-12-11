package ch.heigvd.gamification.to;

import java.io.Serializable;

/**
 *
 * @author Alexandre Perusset
 */
public class SuccessTO implements Serializable {

  private long id;

  private String name;

  private String badge;
  
  public SuccessTO() {
  }

  public SuccessTO(long id, String name, String badge) {
    this.id = id;
    this.name = name;
    this.badge = badge;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
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
}
