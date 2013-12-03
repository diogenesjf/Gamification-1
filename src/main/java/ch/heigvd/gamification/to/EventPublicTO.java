package ch.heigvd.gamification.to;

/**
 * 
 * @author Alexandre Perusset
 */
public class EventPublicTO {
  
  private long id;
  
  private String user;
  
  private String description;
  
  private long timestamp;
  
  private long points;
  
  public EventPublicTO() {
  }
  
  public EventPublicTO(long id, String user, String description, long timestamp, long points) {
    this.id = id;
    this.user = user;
    this.description = description;
    this.timestamp = timestamp;
    this.points = points;
  }
  
  public long getId() {
    return id;
  }
  
  public void setId(long id) {
    this.id = id;
  }
  
  public String getUser() {
    return user;
  }
  
  public void setUser(String user) {
    this.user = user;
  }
  
  public String getDescription() {
    return description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public long getTimestamp() {
    return timestamp;
  }
  
  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }
  
  public long getPoints() {
    return points;
  }
  
  public void setPoints(long points) {
    this.points = points;
  }
}
