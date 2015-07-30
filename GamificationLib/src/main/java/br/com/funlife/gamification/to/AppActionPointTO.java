package br.com.funlife.gamification.to;

import java.io.Serializable;

/**
 * Action point transfert object. This class is used to transfer to the api user, create
 * or update rules remotly or via jax-rs api.
 *
 * @author Diogenes Firmiano
 */
public class AppActionPointTO implements Serializable {

    private long id;

    private long actionID;

    private long pointID;

    private int points;

  //TODO falta colocar multiply/divide by attribute
    public AppActionPointTO() {
    }

    public AppActionPointTO(long id, long actionID, long pointID, int points) {
        this.id = id;
        this.actionID = actionID;
        this.pointID = pointID;
        this.points = points;
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
