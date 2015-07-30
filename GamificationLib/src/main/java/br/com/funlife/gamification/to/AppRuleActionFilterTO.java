package br.com.funlife.gamification.to;

import java.io.Serializable;

/**
 * Rule action transfert object. This class is used to transfer to the api user, create
 * or update rules remotly or via jax-rs api.
 *
 * @author Diogenes Firmiano
 */
public class AppRuleActionFilterTO implements Serializable {

    private long id;

    private long ruleActionID;

    private long attrID;
    
    private int comparator;
    
    private String valueFilter;

    public AppRuleActionFilterTO() {
    }

    public AppRuleActionFilterTO(long id, long ruleActionID, long attrID, int comparator, String valueFilter) {
        this.id = id;
        this.ruleActionID = ruleActionID;
        this.attrID = attrID;
        this.comparator = comparator;
        this.valueFilter = valueFilter;
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

    public long getAttrID() {
        return attrID;
    }

    public void setAttrID(long attrID) {
        this.attrID = attrID;
    }

    public int getComparator() {
        return comparator;
    }

    public void setComparator(int comparator) {
        this.comparator = comparator;
    }

    public String getValueFilter() {
        return valueFilter;
    }

    public void setValueFilter(String valueFilter) {
        this.valueFilter = valueFilter;
    }
    
    
}
