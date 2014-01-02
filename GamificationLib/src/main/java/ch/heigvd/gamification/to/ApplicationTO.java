package ch.heigvd.gamification.to;

import java.io.Serializable;

/**
 * Application transfert object. This class is used to transfer to the api user,
 * create or update applications remotly or via jax-rs api.
 *
 * @author Thomas Moegli
 */
public class ApplicationTO implements Serializable {

  private long id;

  private String name;

  public ApplicationTO() {
  }

  public ApplicationTO(long id, String name) {
    this.id = id;
    this.name = name;
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
}
