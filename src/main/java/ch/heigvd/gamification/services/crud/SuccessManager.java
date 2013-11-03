package ch.heigvd.gamification.services.crud;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.AppUser;
import ch.heigvd.gamification.model.Success;
import ch.heigvd.gamification.services.crud.interfaces.ISuccessManager;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alexandre Perusset
 */
@Stateless
public class SuccessManager implements ISuccessManager {

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
    Success successToUpdate = findById(newState.getId());
    em.merge(newState);
  }

  @Override
  public void delete(long id) throws EntityNotFoundException {
      Success successToDelete = findById(id);
      em.remove(successToDelete);
  }

  @Override
  public Success findById(long id) throws EntityNotFoundException { //TODO
      Success existingSuccess = em.find(Success.class, id);
      if(existingSuccess == null)
          throw new EntityNotFoundException();
      return existingSuccess;
  }

  @Override
  public List<Success> findAll() {
        return em.createNamedQuery("findAllSuccess").getResultList();
  }
}
