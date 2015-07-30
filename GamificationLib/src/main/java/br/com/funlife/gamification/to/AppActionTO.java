package br.com.funlife.gamification.to;

import java.io.Serializable;
import java.util.List;

/**
 * Action transfert object. This class is used to transfer to the api user,
 * create or update actions remotly or via jax-rs api.
 *
 * @author Diogenes Firmiano
 */
public class AppActionTO implements Serializable {

    private long id;

    private String title;

    private int points;

    private List<AppActionPointTO> pointsList;

    private String description;

    public AppActionTO() {
    }

    public AppActionTO(long id, String title, int points, String description) {
        this.id = id;
        this.title = title;
        this.points = points;
        this.description = description;
    }

    public AppActionTO(long id, String title, int points, String description, List<AppActionPointTO> pointsList) {
        this.id = id;
        this.title = title;
        this.points = points;
        this.description = description;
        this.pointsList = pointsList;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<AppActionPointTO> getPointsList() {
        return pointsList;
    }

    public void setPointsList(List<AppActionPointTO> pointsList) {
        this.pointsList = pointsList;
    }

}
