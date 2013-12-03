package ch.heigvd.gamification.services.crud;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Success;
import ch.heigvd.gamification.services.crud.interfaces.local.ISuccessesManagerLocal;
import ch.heigvd.gamification.services.crud.interfaces.remote.ISuccessesManagerRemote;
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
  public long create(Success successData) {
    Success newSuccess = new Success(successData);
    em.persist(newSuccess);
    return newSuccess.getId();
  }

  @Override
  public void update(Success newState) throws EntityNotFoundException {
    findById(newState.getId());
    em.merge(newState);
  }

  @Override
  public void delete(long id) throws EntityNotFoundException {
    em.remove(findById(id));
  }

  @Override
  public Success findById(long id) throws EntityNotFoundException {
    Success existingSuccess = em.find(Success.class, id);
    if (existingSuccess == null) {
      throw new EntityNotFoundException();
    }
    return existingSuccess;
  }

  @Override
  public List<Success> findAll(Application application) {
    return em.createNamedQuery("findAllSuccess")
            .setParameter("appid", application.getId())
            .getResultList();
  }

  @Override
  public void checkRights(long id, Application app) throws EntityNotFoundException, UnauthorizedException {
    if (!findById(id).getApplication().equals(app)) {
      throw new UnauthorizedException();
    }
  }
}
