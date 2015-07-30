package br.com.funlife.gamification.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * This class represent a point.
 *
 * @author Diogenes Firmiano
 */
@NamedQueries({
    @NamedQuery(
            name = "findAllPoints",
            query = "select p from Point p where p.application.id = :appid"
    )
})
@Entity
public class Point implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    private String icon;

    private Double moneyEquivalence;

    //Load events only on demand
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "point")
    private final List<AppActionPoint> actionPoint;

    @ManyToOne
    private Application application;

    public Point() {
        title = "UNDEF";
        description = "UNDEF";
        icon = "UNDEF";
        moneyEquivalence = -1d;
        actionPoint = new LinkedList<>();
    }

    public Point(Point point) {
        title = point.title;
        description = point.description;
        icon = point.icon;
        moneyEquivalence = point.moneyEquivalence;
        actionPoint = point.actionPoint;
        application = point.application;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Double getMoneyEquivalence() {
        return moneyEquivalence;
    }

    public void setMoneyEquivalence(Double moneyEquivalence) {
        this.moneyEquivalence = moneyEquivalence;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Point) || this.id == null) {
            return false;
        }
        Point other = (Point) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "br.com.funlife.gamification.model.Point[id=" + id + "]";
    }
}
