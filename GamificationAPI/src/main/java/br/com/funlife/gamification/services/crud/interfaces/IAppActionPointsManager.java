package br.com.funlife.gamification.services.crud.interfaces;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.exceptions.UnauthorizedException;
import br.com.funlife.gamification.model.AppActionPoint;
import br.com.funlife.gamification.model.Application;
import java.util.List;
import javax.ejb.Local;

/**
 * This interface provides method to create, update, delete and get actions.
 *
 * @author Diogenes Firmiano
 */
@Local
public interface IAppActionPointsManager {

  public long create(AppActionPoint actionTypeData);

  public void update(AppActionPoint newState, Application application) throws EntityNotFoundException, UnauthorizedException;

  public void delete(long id, Application application) throws EntityNotFoundException, UnauthorizedException;

  public AppActionPoint findById(long id, Application application) throws EntityNotFoundException, UnauthorizedException;

  public List<AppActionPoint> findAll(Application application);
}
