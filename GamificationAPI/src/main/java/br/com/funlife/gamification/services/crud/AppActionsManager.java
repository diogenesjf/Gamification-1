package br.com.funlife.gamification.services.crud;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.exceptions.UnauthorizedException;
import br.com.funlife.gamification.model.AppAction;
import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.services.crud.interfaces.IAppActionsManager;
import java.security.InvalidParameterException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Implementation of the actions manager interface.
 *
 * @see IAppActionsManager
 * @author Diogenes Firmiano
 */
@Stateless
public class AppActionsManager implements IAppActionsManager {

  @PersistenceContext(unitName = "Gamification")
  private EntityManager em;

  @Override
  public long create(AppAction action) {
    if (action.getApplication() == null) { //Check if application setted
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
    if (action.getApplication() == null || !action.getApplication().equals(app)) {
      throw new UnauthorizedException();
    }
  }
}
