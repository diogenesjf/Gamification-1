package br.com.funlife.gamification.services.crud.interfaces;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.exceptions.UnauthorizedException;
import br.com.funlife.gamification.model.AppAction;
import br.com.funlife.gamification.model.Application;
import java.util.List;
import javax.ejb.Local;

/**
 * This interface provides method to create, update, delete and get actions.
 *
 * @author Diogenes Firmiano
 */
@Local
public interface IAppActionsManager {

  public long create(AppAction actionTypeData);

  public void update(AppAction newState, Application application) throws EntityNotFoundException, UnauthorizedException;

  public void delete(long id, Application application) throws EntityNotFoundException, UnauthorizedException;

  public AppAction findById(long id, Application application) throws EntityNotFoundException, UnauthorizedException;

  public List<AppAction> findAll(Application application);

  public void checkRights(AppAction action, Application app) throws UnauthorizedException;
}
