package ch.heigvd.gamification.services.crud;

import ch.heigvd.gamification.services.crud.interfaces.local.IRulesManagerLocal;
import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.model.AppAction;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Rule;
import ch.heigvd.gamification.services.crud.interfaces.remote.IRulesManagerRemote;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 *
 * @author Gaël Jobin
 */
@Stateless
@Local(IRulesManagerLocal.class)
@Remote(IRulesManagerRemote.class)
public class RulesManager implements IRulesManagerLocal, IRulesManagerRemote {

  @PersistenceContext(unitName = "Gamification")
  private EntityManager em;

  @Override
  public long create(Rule ruleData) {
    Rule newRule = new Rule(ruleData);
    em.persist(newRule);
    return newRule.getId();
  }

  @Override
  public void update(Rule newState) throws EntityNotFoundException {
    findById(newState.getId());
    em.merge(newState);
  }

  @Override
  public void delete(long id) throws EntityNotFoundException {
    Rule ruleToDelete = findById(id);
    em.remove(ruleToDelete);
  }

  @Override
  public Rule findById(long id) throws EntityNotFoundException {
    Rule existingRule = em.find(Rule.class, id);
    if (existingRule == null) {
      throw new EntityNotFoundException();
    }
    return existingRule;
  }

  @Override
  public List<Rule> findAll(Application application) {
    return em.createNamedQuery("findAllRules")
            .setParameter("appid", application.getId())
            .getResultList();
  }
  
  @Override
  public List<Rule> findAllForAction(AppAction action) {
    return em.createNamedQuery("findAllRulesForAction")
            .setParameter("actionid", action.getId())
            .getResultList();
  }
  
  @Override
  public void checkRights(long id, Application app) throws EntityNotFoundException, UnauthorizedException {
    if (!findById(id).getApplication().equals(app)) {
      throw new UnauthorizedException();
    }
  }
}
