package br.com.funlife.gamification.to;

import java.io.Serializable;

/**
 * Event transfert object. This class is used to transfer to the api user or
 * create events remotly or via jax-rs api. It is not possible to update an
 * existing event.
 *
 * @author Diogenes Firmiano
 */
public class EventTO implements Serializable {

  private long id;

  private long userId;

  private long actionId;

  private long timestamp;

  public EventTO() {
  }

  public EventTO(long id, long userId, long actionId, long timestamp) {
    this.id = id;
    this.userId = userId;
    this.actionId = actionId;
    this.timestamp = timestamp;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public long getActionId() {
    return actionId;
  }

  public void setActionId(long actionId) {
    this.actionId = actionId;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }
}
