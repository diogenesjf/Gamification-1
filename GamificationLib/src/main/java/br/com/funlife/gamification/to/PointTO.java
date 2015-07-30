package br.com.funlife.gamification.to;

import java.io.Serializable;

/**
 * Action transfert object. This class is used to transfer to the api user,
 * create or update actions remotly or via jax-rs api.
 *
 * @author Diogenes Firmiano
 */
public class PointTO implements Serializable {

    private long id;

    private String title;

    private String description;

    private String icon;

    private double moneyEquivalence;

    public PointTO() {
    }

    public PointTO(long id, String title, String icon, String description) {
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMoneyEquivalence() {
        return moneyEquivalence;
    }

    public void setMoneyEquivalence(double moneyEquivalence) {
        this.moneyEquivalence = moneyEquivalence;
    }

}
