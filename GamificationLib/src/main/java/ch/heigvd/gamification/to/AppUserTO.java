package ch.heigvd.gamification.to;

import java.io.Serializable;

/**
 *
 * @author Alexandre Perusset
 */
public class AppUserTO implements Serializable {

  private long id;

  private String name;

  private String surname;

  private String nickname;

  private String password;

  public AppUserTO() {
  }

  public AppUserTO(long id, String name, String surname, String nickname, String password) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.nickname = nickname;
    this.password = password;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
