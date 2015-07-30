package br.com.funlife.gamification.to;

import java.io.Serializable;

/**
 * Action point transfert object. This class is used to transfer to the api user, create
 * or update rules remotly or via jax-rs api.
 *
 * @author Diogenes Firmiano
 */
public class AppRulePointTO implements Serializable {

    private long id;

    private long ruleID;

    private long pointID;

    private int points;

    public AppRulePointTO() {
    }

    public AppRulePointTO(long id, long ruleID, long pointID, int points) {
        this.id = id;
        this.ruleID = ruleID;
        this.pointID = pointID;
        this.points = points;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRuleID() {
        return ruleID;
    }

    public void setRuleID(long ruleID) {
        this.ruleID = ruleID;
    }


    public long getPointID() {
        return pointID;
    }

    public void setPointID(long pointID) {
        this.pointID = pointID;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}
