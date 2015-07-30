package br.com.funlife.gamification.services.crud;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.exceptions.UnauthorizedException;
import br.com.funlife.gamification.model.AppActionPoint;
import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.services.crud.interfaces.IAppActionPointsManager;
import br.com.funlife.gamification.services.crud.interfaces.IAppActionsManager;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Implementation of the action points manager interface.
 *
 * @see IAppActionsManager
 * @author Diogenes Firmiano
 */
@Stateless
public class AppActionPointsManager implements IAppActionPointsManager {

  @PersistenceContext(unitName = "Gamification")
  private EntityManager em;

  @Override
  public long create(AppActionPoint action) {
    AppActionPoint newAction = new AppActionPoint(action);
    em.persist(newAction);
    return newAction.getId();
  }

  @Override
  public void update(AppActionPoint newState, Application application) throws EntityNotFoundException, UnauthorizedException {
    findById(newState.getId(), application);
    em.merge(newState);
  }

  @Override
  public void delete(long id, Application application) throws EntityNotFoundException, UnauthorizedException {
    em.remove(findById(id, application));
  }

  @Override
  public AppActionPoint findById(long id, Application application) throws EntityNotFoundException, UnauthorizedException {
    AppActionPoint action = em.find(AppActionPoint.class, id);
    if (action == null) {
      throw new EntityNotFoundException();
    }
    return action;
  }

  @Override
  public List<AppActionPoint> findAll(Application application) {
    return em.createNamedQuery("findAllAppActions")
            .setParameter("appid", application.getId())
            .getResultList();
  }

}
