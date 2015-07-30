package br.com.funlife.gamification.services.crud.interfaces;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.exceptions.UnauthorizedException;
import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.model.Point;
import java.util.List;
import javax.ejb.Local;

/**
 * This interface provides method to create, update, delete and get points.
 *
 * @author Diogenes Firmiano
 */
@Local
public interface IPointsManager {

  public long create(Point pointData);

  public void update(Point newState, Application app) throws EntityNotFoundException, UnauthorizedException;

  public void delete(long id, Application app) throws EntityNotFoundException, UnauthorizedException;

  public Point findById(long id, Application app) throws EntityNotFoundException, UnauthorizedException;

  public List<Point> findAll(Application app);

  public void checkRights(Point point, Application app) throws UnauthorizedException;
}
