package ch.heigvd.gamification.services.crud;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.model.AppAction;
import ch.heigvd.gamification.model.AppUser;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Rule;
import ch.heigvd.gamification.model.Success;
import ch.heigvd.gamification.services.crud.interfaces.local.IAppUsersManagerLocal;
import ch.heigvd.gamification.services.crud.interfaces.local.IRulesManagerLocal;
import ch.heigvd.gamification.services.crud.interfaces.local.ISuccessesManagerLocal;
import ch.heigvd.gamification.services.crud.interfaces.remote.IAppUsersManagerRemote;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
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

  @EJB
  private ISuccessesManagerLocal successesManager;
  
  @EJB
  private IRulesManagerLocal rulesManager;

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
    return em.createNamedQuery("findAllWithSuccess")
            .setParameter("successid", id)
            .getResultList();
  }

  @Override
  public List<Success> checkForNewSuccesses(AppUser user) {
    List<Rule> matchingRules = new LinkedList<>(), actionRules;
    List<Success> notAquiredSuccesses = successesManager.findAll(user.getApplication()),
            aquiredSuccesses = new LinkedList<>();
    //First step : get the matching rules
    AppAction action;
    long points;
    List<Object[]> actionPoints = em.createNamedQuery("findAllActionPointsForUser")
            .setParameter("userid", user.getId()).getResultList();
    for (Object[] ap : actionPoints) {
      action = (AppAction) ap[0];
      points = (Long) ap[1];
      actionRules = rulesManager.findAllForAction(action);
      for (Rule r : actionRules) {
        if (r.getGoalPoints() <= points) {
          matchingRules.add(r);
        }
      }
    }
    //Second step : define which successes can be obtained
    for (Success s : user.getSuccesses()) {
      notAquiredSuccesses.remove(s);
    }
    //Third (and last) step : check if a new success is acquired
    boolean acquired;
    for (Success s : notAquiredSuccesses) {
      acquired = true;
      for (Rule r : s.getRules()) {
        if (!matchingRules.contains(r)) {
          acquired = false;
          break;
        }
      }
      if (acquired) {
        aquiredSuccesses.add(s);
      }
    }
    return aquiredSuccesses;
  }

  @Override
  public void checkRights(long id, Application app) throws EntityNotFoundException, UnauthorizedException {
    if (!findById(id).getApplication().equals(app)) {
      throw new UnauthorizedException();
    }
  }
}
