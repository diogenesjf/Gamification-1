package ch.heigvd.gamification.services.crud;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Success;
import ch.heigvd.gamification.services.crud.interfaces.local.ISuccessesManagerLocal;
import ch.heigvd.gamification.services.crud.interfaces.remote.ISuccessesManagerRemote;
import java.security.InvalidParameterException;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alexandre Perusset
 */
@Stateless
@Local(ISuccessesManagerLocal.class)
@Remote(ISuccessesManagerRemote.class)
public class SuccessesManager implements ISuccessesManagerLocal, ISuccessesManagerRemote {

  @PersistenceContext(unitName = "Gamification")
  private EntityManager em;

  @Override
  public long create(Success success) {
    if ( success.getApplication() == null ) { //Check if application setted
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
