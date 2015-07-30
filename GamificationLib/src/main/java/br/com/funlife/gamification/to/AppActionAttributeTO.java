package br.com.funlife.gamification.to;

import java.io.Serializable;

/**
 * Action Attribute transfert object. This class is used to transfer to the api user, create
 * or update rules remotly or via jax-rs api.
 *
 * @author Diogenes Firmiano
 */
public class AppActionAttributeTO implements Serializable {


    private long id;

    private long actionID;

    private String name;

    private int type;

    public AppActionAttributeTO() {
    }

    public AppActionAttributeTO(long id, long actionID, String name, int type) {
        this.id = id;
        this.actionID = actionID;
        this.name = name;
        this.type = type;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


}
