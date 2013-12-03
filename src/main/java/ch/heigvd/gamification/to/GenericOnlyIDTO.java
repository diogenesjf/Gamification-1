package ch.heigvd.gamification.to;

/**
 * This is a generic TO when you want to pass through JSON a simple ID and not
 * the whole representation
 *
 * @author Gaël Jobin
 */
public class GenericOnlyIDTO {

  private long id;

  public GenericOnlyIDTO() {
  }

  public GenericOnlyIDTO(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
