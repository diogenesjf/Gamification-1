package ch.heigvd.gamification.services.crud.interfaces;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.model.AppAction;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Rule;
import java.util.List;
import javax.ejb.Local;

/**
 * This interface provides method to create, update, delete and get rules. It is
 * also possible de get all rules linked to a given action.
 *
 * @author Gaël Jobin
 */
@Local
public interface IRulesManager {

  public long create(Rule employeeData);

  public void update(Rule newState, Application application) throws EntityNotFoundException, UnauthorizedException;

  public void delete(long id, Application applicaiton) throws EntityNotFoundException, UnauthorizedException;

  public Rule findById(long id, Application application) throws EntityNotFoundException, UnauthorizedException;

  public List<Rule> findAll(Application application);

  public List<Rule> findAllForAction(AppAction action);

  public void checkRights(Rule rule, Application app) throws UnauthorizedException;
}
