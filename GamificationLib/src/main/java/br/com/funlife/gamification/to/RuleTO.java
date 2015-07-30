package br.com.funlife.gamification.to;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Rule transfert object. This class is used to transfer to the api user, create
 * or update rules remotly or via jax-rs api.
 *
 * @author Diogenes Firmiano
 */
public class RuleTO implements Serializable {

    private long id;

    private String name;

    private String description;

    private int goalPoints;

    private long actionID;

    private int actionCompletionType;

    private Timestamp initialDatetime;

    private Timestamp finalDatetime;

    private int qtyAmountRestriction;

    private int typeAmountRestriction;

    private int qtyTimeAmountRestriction;

    private int typeTimeAmountRestriction;

    public RuleTO() {
    }

    public RuleTO(long ruleId, String name, String description, int goalPoints, long actionID) {
        this.id = ruleId;
        this.name = name;
        this.description = description;
        this.goalPoints = goalPoints;
        this.actionID = actionID;
    }

    public long getId() {
        return id;
    }

    public void setId(long ruleId) {
        this.id = ruleId;
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

    public int getActionCompletionType() {
        return actionCompletionType;
    }

    public void setActionCompletionType(int actionCompletionType) {
        this.actionCompletionType = actionCompletionType;
    }

    public Timestamp getInitialDatetime() {
        return initialDatetime;
    }

    public void setInitialDatetime(Timestamp initialDatetime) {
        this.initialDatetime = initialDatetime;
    }

    public Timestamp getFinalDatetime() {
        return finalDatetime;
    }

    public void setFinalDatetime(Timestamp finalDatetime) {
        this.finalDatetime = finalDatetime;
    }

    public int getQtyAmountRestriction() {
        return qtyAmountRestriction;
    }

    public void setQtyAmountRestriction(int qtyAmountRestriction) {
        this.qtyAmountRestriction = qtyAmountRestriction;
    }

    public int getTypeAmountRestriction() {
        return typeAmountRestriction;
    }

    public void setTypeAmountRestriction(int typeAmountRestriction) {
        this.typeAmountRestriction = typeAmountRestriction;
    }

    public int getQtyTimeAmountRestriction() {
        return qtyTimeAmountRestriction;
    }

    public void setQtyTimeAmountRestriction(int qtyTimeAmountRestriction) {
        this.qtyTimeAmountRestriction = qtyTimeAmountRestriction;
    }

    public int getTypeTimeAmountRestriction() {
        return typeTimeAmountRestriction;
    }

    public void setTypeTimeAmountRestriction(int typeTimeAmountRestriction) {
        this.typeTimeAmountRestriction = typeTimeAmountRestriction;
    }

}
