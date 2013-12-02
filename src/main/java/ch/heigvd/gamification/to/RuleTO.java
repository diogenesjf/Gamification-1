package ch.heigvd.gamification.to;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gaël Jobin
 */
@XmlRootElement(name = "rule")
public class RuleTO {

  private long ruleId;

  private String name;
  private String description;
  private int acquiredPoints;
  private long actionTypeId;

  public RuleTO() {
  }

  public RuleTO(long ruleId, String name, String description, int acquiredPoints, long actionTypeId) {
    this.ruleId = ruleId;
    this.name = name;
    this.description = description;
    this.acquiredPoints = acquiredPoints;
    this.actionTypeId = actionTypeId;
  }

  public long getId() {
    return ruleId;
  }

  public void setId(long ruleId) {
    this.ruleId = ruleId;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getAcquiredPoints() {
    return this.acquiredPoints;
  }

  public void setAcquiredPoints(int acquiredPoints) {
    this.acquiredPoints = acquiredPoints;
  }

  public long getActionTypeId() {
    return this.actionTypeId;
  }

  public void setActionTypeId(long actionTypeId) {
    this.actionTypeId = actionTypeId;
  }

}
