package ch.heigvd.gamification.services.crud;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.AppUser;
import ch.heigvd.gamification.services.crud.interfaces.IAppUsersManager;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alexandre Perusset
 */
@Stateless
public class AppUsersManager implements IAppUsersManager {

  @PersistenceContext(unitName="Gamification")
  private EntityManager em;

  @Override
  public long create(AppUser userData) {
    AppUser newUser = new AppUser(userData);
    em.persist(newUser);
    return newUser.getId();
  }

  @Override
  public void update(AppUser newState) throws EntityNotFoundException {
    findById(newState.getId());
    em.merge(newState);
  }

  @Override
  public void delete(long id) throws EntityNotFoundException {
    em.remove(findById(id));
  }

  @Override
  public AppUser findById(long id) throws EntityNotFoundException {
    AppUser findUser = em.find(AppUser.class, id);
    if (findUser == null) {
      throw new EntityNotFoundException("Cannot find User with id " + id);
    }
    return findUser;
  }

  @Override
  public List<AppUser> findAll() {
    return em.createNamedQuery("findAllUsers").getResultList();
  }
  
  @Override
  public List<AppUser> findAllBySuccess(long id) {
    return em.createQuery("SELECT u FROM AppUser u, AppUser_Success us WHERE u.id = us.users_id AND us.successes.id = :id").setParameter("id", id).getResultList();
  }
}
