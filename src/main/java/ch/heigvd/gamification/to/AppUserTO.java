package ch.heigvd.gamification.to;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alexandre Perusset
 */
@XmlRootElement
public class AppUserTO {

  private long id;

  private String name;

  private String surname;

  private String nickname;

  public AppUserTO() {
  }

  public AppUserTO(long id, String name, String surname, String nickname) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.nickname = nickname;
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

}
