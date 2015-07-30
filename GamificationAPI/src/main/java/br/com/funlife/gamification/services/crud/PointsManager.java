package br.com.funlife.gamification.services.crud;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.exceptions.UnauthorizedException;
import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.model.Point;
import br.com.funlife.gamification.model.Success;
import br.com.funlife.gamification.services.crud.interfaces.IPointsManager;
import java.security.InvalidParameterException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Implementation of the points manager interface.
 *
 * @see IPointsManager
 * @author Diogenes Firmiano
 */
@Stateless
public class PointsManager implements IPointsManager {

  @PersistenceContext(unitName = "Gamification")
  private EntityManager em;

  @Override
  public long create(Point point) {
    if (point.getApplication() == null) { //Check if application setted
      throw new InvalidParameterException("Cannot save a point without application");
    }
    Point newPoint = new Point(point);
    em.persist(newPoint);
    return newPoint.getId();
  }

  @Override
  public void update(Point newState, Application app) throws EntityNotFoundException, UnauthorizedException {
    findById(newState.getId(), app);
    em.merge(newState);
  }

  @Override
  public void delete(long id, Application app) throws EntityNotFoundException, UnauthorizedException {
    em.remove(findById(id, app));
  }

  @Override
  public Point findById(long id, Application app) throws EntityNotFoundException, UnauthorizedException {
    Point point = em.find(Point.class, id);
    if (point == null) {
      throw new EntityNotFoundException();
    }
    checkRights(point, app);
    return point;
  }

  @Override
  public List<Point> findAll(Application app) {
    return em.createNamedQuery("findAllPoints")
            .setParameter("appid", app.getId())
            .getResultList();
  }

  @Override
  public void checkRights(Point point, Application app) throws UnauthorizedException {
    if (point.getApplication() == null || !point.getApplication().equals(app)) {
      throw new UnauthorizedException();
    }
  }
}
