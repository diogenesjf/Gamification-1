package ch.heigvd.gamification.services.crud;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.model.AppUser;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.services.crud.interfaces.local.IAppUsersManagerLocal;
import ch.heigvd.gamification.services.crud.interfaces.remote.IAppUsersManagerRemote;
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
@Local(IAppUsersManagerLocal.class)
@Remote(IAppUsersManagerRemote.class)
public class AppUsersManager implements IAppUsersManagerLocal, IAppUsersManagerRemote {

  @PersistenceContext(unitName = "Gamification")
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
  public List<AppUser> findAll(Application application) {
    return em.createNamedQuery("findAllUsers")
            .setParameter("appid", application.getId())
            .getResultList();
  }

  @Override
  public List<AppUser> findAllBySuccess(long id) {
    return em.createNamedQuery("findAllBySuccess")
            .setParameter("successid", id)
            .getResultList();
  }

  @Override
  public void checkRights(long id, Application app) throws EntityNotFoundException, UnauthorizedException {
    if (!findById(id).getApplication().equals(app)) {
      throw new UnauthorizedException();
    }
  }
}
