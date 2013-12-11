package ch.heigvd.gamification.to;

import java.io.Serializable;

/**
 *
 * @author Gaël Jobin
 */
public class AppActionTO implements Serializable {

  private long id;

  private String title;

  private int points;

  private String description;

  public AppActionTO() {
  }

  public AppActionTO(long id, String title, int points, String description) {
    this.id = id;
    this.title = title;
    this.points = points;
    this.description = description;
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
}
