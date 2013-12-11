package ch.heigvd.gamification.to;

import java.io.Serializable;

/**
 *
 * @author Gaël Jobin
 */
public class RuleTO implements Serializable {

  private long ruleId;

  private String name;

  private String description;

  private int goalPoints;

  private long actionID;

  public RuleTO() {
  }

  public RuleTO(long ruleId, String name, String description, int goalPoints, long actionID) {
    this.ruleId = ruleId;
    this.name = name;
    this.description = description;
    this.goalPoints = goalPoints;
    this.actionID = actionID;
  }

  public long getId() {
    return ruleId;
  }

  public void setId(long ruleId) {
    this.ruleId = ruleId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getGoalPoints() {
    return goalPoints;
  }

  public void setGoalPoints(int goalPoints) {
    this.goalPoints = goalPoints;
  }

  public long getActionID() {
    return actionID;
  }

  public void setActionID(long actionID) {
    this.actionID = actionID;
  }
}
