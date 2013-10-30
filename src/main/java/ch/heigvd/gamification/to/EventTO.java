package ch.heigvd.gamification.to;

/**
 * Cette classe permet d'insérer pour un utilisateur et un type d'action donnés,
 * un événement dans la base de données.
 *
 * @author Alexandre Perusset
 */
public class EventTO {

  private long userId;

  private long actionTypeId;

  private long timestamp;

  public EventTO() {
  }

  public EventTO(long userId, long actionTypeId, long timestamp) {
    this.userId = userId;
    this.actionTypeId = actionTypeId;
    this.timestamp = timestamp;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public long getActionTypeId() {
    return actionTypeId;
  }

  public void setActionTypeId(long actionTypeId) {
    this.actionTypeId = actionTypeId;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }
}
