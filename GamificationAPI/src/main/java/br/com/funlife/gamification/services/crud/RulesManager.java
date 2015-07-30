package br.com.funlife.gamification.services.crud;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.exceptions.UnauthorizedException;
import br.com.funlife.gamification.model.AppAction;
import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.model.Rule;
import br.com.funlife.gamification.services.crud.interfaces.IRulesManager;
import java.security.InvalidParameterException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Implementation of the rules manager interface.
 *
 * @see IRulesManager
 * @author Diogenes Firmiano
 */
@Stateless
public class RulesManager implements IRulesManager {

  @PersistenceContext(unitName = "Gamification")
  private EntityManager em;

  @Override
  public long create(Rule rule) {
    if (rule.getApplication() == null) { //Check if application setted
      throw new InvalidParameterException("Cannot save a rule without application");
    }
    Rule newRule = new Rule(rule);
    em.persist(newRule);
    return newRule.getId();
  }

  @Override
  public void update(Rule newState, Application application) throws EntityNotFoundException, UnauthorizedException {
    findById(newState.getId(), application);
    em.merge(newState);
  }

  @Override
  public void delete(long id, Application application) throws EntityNotFoundException, UnauthorizedException {
    em.remove(findById(id, application));
  }

  @Override
  public Rule findById(long id, Application application) throws EntityNotFoundException, UnauthorizedException {
    Rule rule = em.find(Rule.class, id);
    if (rule == null) {
      throw new EntityNotFoundException();
    }
    checkRights(rule, application);
    return rule;
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
  public void checkRights(Rule rule, Application app) throws UnauthorizedException {
    if (rule.getApplication() == null || !rule.getApplication().equals(app)) {
      throw new UnauthorizedException();
    }
  }
}
