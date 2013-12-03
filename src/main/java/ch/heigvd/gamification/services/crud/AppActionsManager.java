package ch.heigvd.gamification.services.crud;

import ch.heigvd.gamification.services.crud.interfaces.local.IAppActionsManagerLocal;
import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.model.AppAction;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Event;
import ch.heigvd.gamification.model.Rule;
import ch.heigvd.gamification.services.crud.interfaces.remote.IAppActionsManagerRemote;
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
  public long create(AppAction actionTypeData) {
    AppAction newActionType = new AppAction(actionTypeData);
    em.persist(newActionType);
    return newActionType.getId();
  }

  @Override
  public void update(AppAction newState) throws EntityNotFoundException {
    findById(newState.getId());
    em.merge(newState);
  }

  @Override
  public void delete(long id) throws EntityNotFoundException {
    AppAction actionTypeToDelete = findById(id);
    em.remove(actionTypeToDelete);
  }

  @Override
  public AppAction findById(long id) throws EntityNotFoundException {
    AppAction existingActionType = em.find(AppAction.class, id);
    if (existingActionType == null) {
      throw new EntityNotFoundException();
    }
    return existingActionType;
  }

  @Override
  public List<AppAction> findAll(Application application) {
    return em.createNamedQuery("findAllAppActions")
            .setParameter("appid", application.getId())
            .getResultList();
  }

  @Override
  public List<Event> findAllActions() { //TODO
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public List<Rule> findAllRules() { //TODO
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void checkRights(long id, Application app) throws EntityNotFoundException, UnauthorizedException {
    if (!findById(id).getApplication().equals(app)) {
      throw new UnauthorizedException();
    }
  }
}
