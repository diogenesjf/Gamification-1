/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.to;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alexandre Perusset
 */
@XmlRootElement
public class UserActionTO {

  private long id;

  private String title;

  private String description;

  private int points;

  public UserActionTO() {
  }
  
  public UserActionTO(long id, String title, String description, int points) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.points = points;
  }
  
  public long getId() {
    return id;
  }

  public void setId(long id) {
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

}
