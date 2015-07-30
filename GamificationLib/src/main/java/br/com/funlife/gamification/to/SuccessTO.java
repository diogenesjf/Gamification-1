package br.com.funlife.gamification.to;

import java.io.Serializable;

/**
 * Success transfert object. This class is used to transfer to the api user,
 * create or update successes remotly or via jax-rs api.
 *
 * @author Diogenes Firmiano
 */
public class SuccessTO implements Serializable {

    private long id;

    private String name;

    private String badge;

    private int type;

    public SuccessTO() {
    }

    public SuccessTO(long id, String name, String badge) {
        this.id = id;
        this.name = name;
        this.badge = badge;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
