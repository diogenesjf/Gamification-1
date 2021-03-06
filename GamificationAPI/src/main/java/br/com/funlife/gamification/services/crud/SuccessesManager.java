package br.com.funlife.gamification.services.crud;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.exceptions.UnauthorizedException;
import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.model.Success;
import br.com.funlife.gamification.services.crud.interfaces.ISuccessesManager;
import java.security.InvalidParameterException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Implementation of the successes manager interface.
 *
 * @see ISuccessesManager
 * @author Diogenes Firmiano
 */
@Stateless
public class SuccessesManager implements ISuccessesManager {

  @PersistenceContext(unitName = "Gamification")
  private EntityManager em;

  @Override
  public long create(Success success) {
    if (success.getApplication() == null) { //Check if application setted
      throw new InvalidParameterException("Cannot save a success without application");
    }
    Success newSuccess = new Success(success);
    em.persist(newSuccess);
    return newSuccess.getId();
  }

  @Override
  public void update(Success newState, Application app) throws EntityNotFoundException, UnauthorizedException {
    findById(newState.getId(), app);
    em.merge(newState);
  }

  @Override
  public void delete(long id, Application app) throws EntityNotFoundException, UnauthorizedException {
    em.remove(findById(id, app));
  }

  @Override
  public Success findById(long id, Application app) throws EntityNotFoundException, UnauthorizedException {
    Success success = em.find(Success.class, id);
    if (success == null) {
      throw new EntityNotFoundException();
    }
    checkRights(success, app);
    return success;
  }

  @Override
  public List<Success> findAll(Application app) {
    return em.createNamedQuery("findAllSuccess")
            .setParameter("appid", app.getId())
            .getResultList();
  }

  @Override
  public void checkRights(Success success, Application app) throws UnauthorizedException {
    if (success.getApplication() == null || !success.getApplication().equals(app)) {
      throw new UnauthorizedException();
    }
  }
}
