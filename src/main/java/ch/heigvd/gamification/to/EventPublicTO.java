package ch.heigvd.gamification.to;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Alexandre Perusset
 */
@XmlRootElement(name="event")
public class EventPublicTO {
  
  private long id;
  
  private String userInfos;
  
  private String actionTypeInfos;
  
  private long timestamp;
  
  public EventPublicTO() {
    
  }
  
  public EventPublicTO(long id, String userInfos, String actionTypeInfos, long timestamp) {
    this.id = id;
    this.userInfos = userInfos;
    this.actionTypeInfos = actionTypeInfos;
    this.timestamp = timestamp;
  }
  
  public long getId() {
    return id;
  }
  
  public void setId(long id) {
    this.id = id;
  }
  
  public String getUserInfos() {
    return userInfos;
  }
  
  public void setUserInfos(String userInfos) {
    this.userInfos = userInfos;
  }
  
  public String getActionTypeInfos() {
    return actionTypeInfos;
  }
  
  public void setActionTypeInfos(String actionTypeInfos) {
    this.actionTypeInfos = actionTypeInfos;
  }
  
  public long getTimestamp() {
    return timestamp;
  }
  
  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }
  
}
