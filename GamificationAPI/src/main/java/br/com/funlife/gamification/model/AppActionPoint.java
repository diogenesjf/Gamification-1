package br.com.funlife.gamification.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * This class is an Action. An action can be performed by a user. When a user
 * perform an action, this is called an Event.
 *
 * @author Diogenes Firmiano
 */
@NamedQueries({
    @NamedQuery(
            name = "findAllAppActions",
            query = "select a from AppAction a where a.application.id = :appid"
    ),
    @NamedQuery(
            name = "findAllActionPointsForUser",
            query = "select a, sum(a.points) as points "
            + "from AppUser u inner join u.events e inner join e.action a "
            + "where u.id = :userid group by a"
    )
})
@Entity
public class AppActionPoint implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int points;

    @ManyToOne
    private AppAction action;

    @ManyToOne
    private Point point;

    public AppActionPoint() {
        points = -1;
        action = null;
        point = null;
    }

    public AppActionPoint(AppActionPoint actionPoint) {
        this.points = actionPoint.points;
        this.action = actionPoint.action;
        this.point = actionPoint.point;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public AppAction getAction() {
        return action;
    }

    public void setAction(AppAction action) {
        this.action = action;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AppActionPoint) || this.id == null) {
            return false;
        }
        AppActionPoint other = (AppActionPoint) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "br.com.funlife.gamification.model.AppActionPoint[id=" + id + "]";
    }
}
