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
@XmlRootElement(name="success")
public class SuccessTO {

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
