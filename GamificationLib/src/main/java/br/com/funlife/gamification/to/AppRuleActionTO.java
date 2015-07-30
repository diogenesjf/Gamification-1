package br.com.funlife.gamification.to;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Rule action transfert object. This class is used to transfer to the api user, create
 * or update rules remotly or via jax-rs api.
 *
 * @author Diogenes Firmiano
 */
public class AppRuleActionTO implements Serializable {

    private long id;

    private long actionID;

    private long ruleID;

    private Timestamp initialDate;

    private Timestamp finalDate;

    private int frequency;
    
    private int qtyToComplete;
    
    private int order;
    
    private boolean mandatory = false;

    public AppRuleActionTO() {
    }

    public AppRuleActionTO(long id, long actionID, long ruleID) {
        this.id = id;
        this.actionID = actionID;
        this.ruleID = ruleID;
    }

    public AppRuleActionTO(long id, long actionID, long ruleID, Timestamp initialDate, Timestamp finalDate, int frequency, int qtyToComplete, int order, boolean mandatory) {
        this.id = id;
        this.actionID = actionID;
        this.ruleID = ruleID;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.frequency = frequency;
        this.qtyToComplete = qtyToComplete;
        this.order = order;
        this.mandatory = mandatory;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getActionID() {
        return actionID;
    }

    public void setActionID(long actionID) {
        this.actionID = actionID;
    }

    public long getRuleID() {
        return ruleID;
    }

    public void setRuleID(long ruleID) {
        this.ruleID = ruleID;
    }

    public Timestamp getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Timestamp initialDate) {
        this.initialDate = initialDate;
    }

    public Timestamp getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Timestamp finalDate) {
        this.finalDate = finalDate;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getQtyToComplete() {
        return qtyToComplete;
    }

    public void setQtyToComplete(int qtyToComplete) {
        this.qtyToComplete = qtyToComplete;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }
    
    
    
}
