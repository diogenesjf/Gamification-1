package br.com.funlife.gamification.to;

import java.io.Serializable;

/**
 * Action point transfert object. This class is used to transfer to the api user, create
 * or update rules remotly or via jax-rs api.
 *
 * @author Diogenes Firmiano
 */
public class AppRulePreReqTO implements Serializable {

    private long id;

    private long ruleActionID;

    private int qtyItems;

    private int itemType;

    private long pointItemID;

    private long catalogItemID;

    private long ruleItemID;

    private long successItemID;

    public AppRulePreReqTO() {
    }

    public AppRulePreReqTO(long id, long ruleActionID, int qtyItems, int itemType, long pointItemID, long catalogItemID, long ruleItemID, long successItemID) {
        this.id = id;
        this.ruleActionID = ruleActionID;
        this.qtyItems = qtyItems;
        this.itemType = itemType;
        this.pointItemID = pointItemID;
        this.catalogItemID = catalogItemID;
        this.ruleItemID = ruleItemID;
        this.successItemID = successItemID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRuleActionID() {
        return ruleActionID;
    }

    public void setRuleActionID(long ruleActionID) {
        this.ruleActionID = ruleActionID;
    }

    public int getQtyItems() {
        return qtyItems;
    }

    public void setQtyItems(int qtyItems) {
        this.qtyItems = qtyItems;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public long getPointItemID() {
        return pointItemID;
    }

    public void setPointItemID(long pointItemID) {
        this.pointItemID = pointItemID;
    }

    public long getCatalogItemID() {
        return catalogItemID;
    }

    public void setCatalogItemID(long catalogItemID) {
        this.catalogItemID = catalogItemID;
    }

    public long getRuleItemID() {
        return ruleItemID;
    }

    public void setRuleItemID(long ruleItemID) {
        this.ruleItemID = ruleItemID;
    }

    public long getSuccessItemID() {
        return successItemID;
    }

    public void setSuccessItemID(long successItemID) {
        this.successItemID = successItemID;
    }
 
    
    
}
