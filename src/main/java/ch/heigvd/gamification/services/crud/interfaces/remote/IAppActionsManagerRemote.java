package ch.heigvd.gamification.services.crud.interfaces.remote;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.model.AppAction;
import ch.heigvd.gamification.model.Application;
import java.util.List;

/**
 *
 * @author Gaël Jobin
 */
public interface IAppActionsManagerRemote {

  public long create(AppAction actionTypeData);

  public void update(AppAction newState, Application application) throws EntityNotFoundException, UnauthorizedException;

  public void delete(long id, Application application) throws EntityNotFoundException, UnauthorizedException;

  public AppAction findById(long id, Application application) throws EntityNotFoundException, UnauthorizedException;

  public List<AppAction> findAll(Application application);

  public void checkRights(AppAction action, Application app) throws UnauthorizedException;
}
