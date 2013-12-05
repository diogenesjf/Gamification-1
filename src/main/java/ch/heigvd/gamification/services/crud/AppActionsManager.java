package ch.heigvd.gamification.services.crud;

import ch.heigvd.gamification.services.crud.interfaces.local.IAppActionsManagerLocal;
import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.model.AppAction;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.services.crud.interfaces.remote.IAppActionsManagerRemote;
import java.security.InvalidParameterException;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gaël Jobin
 */
@Stateless
@Local(IAppActionsManagerLocal.class)
@Remote(IAppActionsManagerRemote.class)
public class AppActionsManager implements IAppActionsManagerLocal, IAppActionsManagerRemote {

  @PersistenceContext(unitName = "Gamification")
  private EntityManager em;

  @Override
  public long create(AppAction action) {
    if ( action.getApplication() == null ) { //Check if application setted
      throw new InvalidParameterException("Cannot save an AppAction without application");
    }
    AppAction newAction = new AppAction(action);
    em.persist(newAction);
    return newAction.getId();
  }

  @Override
  public void update(AppAction newState, Application application) throws EntityNotFoundException, UnauthorizedException {
    findById(newState.getId(), application);
    em.merge(newState);
  }

  @Override
  public void delete(long id, Application application) throws EntityNotFoundException, UnauthorizedException {
    em.remove(findById(id, application));
  }

  @Override
  public AppAction findById(long id, Application application) throws EntityNotFoundException, UnauthorizedException {
    AppAction action = em.find(AppAction.class, id);
    if (action == null) {
      throw new EntityNotFoundException();
    }
    checkRights(action, application);
    return action;
  }

  @Override
  public List<AppAction> findAll(Application application) {
    return em.createNamedQuery("findAllAppActions")
            .setParameter("appid", application.getId())
            .getResultList();
  }

  @Override
  public void checkRights(AppAction action, Application app) throws UnauthorizedException {
    if (action.getApplication() == null || action.getApplication().equals(app)) {
      throw new UnauthorizedException();
    }
  }
}
