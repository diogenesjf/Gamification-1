package br.com.funlife.gamification.services.crud;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.exceptions.UnauthorizedException;
import br.com.funlife.gamification.model.AppAction;
import br.com.funlife.gamification.model.AppUser;
import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.model.Rule;
import br.com.funlife.gamification.model.Success;
import br.com.funlife.gamification.services.crud.interfaces.IAppUsersManager;
import br.com.funlife.gamification.services.crud.interfaces.IRulesManager;
import br.com.funlife.gamification.services.crud.interfaces.ISuccessesManager;
import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Implementation of the users manager interface.
 *
 * @see IAppUsersManager
 * @author Diogenes Firmiano
 */
@Stateless
public class AppUsersManager implements IAppUsersManager {

  @PersistenceContext(unitName = "Gamification")
  private EntityManager em;

  @EJB
  private ISuccessesManager successesManager;

  @EJB
  private IRulesManager rulesManager;

  @Override
  public long create(AppUser user) {
    if (user.getApplication() == null) { //Check if application setted
      throw new InvalidParameterException("Cannot save an AppUser without application");
    }
    AppUser newUser = new AppUser(user);
    em.persist(newUser);
    return newUser.getId();
  }

  @Override
  public void update(AppUser newState, Application application) throws EntityNotFoundException, UnauthorizedException {
    findById(newState.getId(), application);
    em.merge(newState);
  }

  @Override
  public void delete(long id, Application application) throws EntityNotFoundException, UnauthorizedException {
    em.remove(findById(id, application));
  }

  @Override
  public AppUser findById(long id, Application app) throws EntityNotFoundException, UnauthorizedException {
    AppUser user = em.find(AppUser.class, id);
    if (user == null) {
      throw new EntityNotFoundException("Cannot find User with id " + id);
    }
    checkRights(user, app);
    return user;
  }

  @Override
  public List<AppUser> findAll(Application app) {
    return em.createNamedQuery("findAllUsers")
            .setParameter("appid", app.getId())
            .getResultList();
  }

  @Override
  public List<AppUser> findAllBySuccess(Success success, Application app) throws UnauthorizedException {
    successesManager.checkRights(success, app);
    return em.createNamedQuery("findAllWithSuccess")
            .setParameter("successid", success.getId())
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
  public void checkRights(AppUser user, Application app) throws UnauthorizedException {
    if (user.getApplication() == null || !user.getApplication().equals(app)) {
      throw new UnauthorizedException();
    }
  }
}
