package ch.heigvd.gamification.services.crud;

import ch.heigvd.gamification.services.crud.interfaces.IRulesManager;
import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Rule;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 
 * 
 * @author Gaël Jobin
 */
@Stateless
public class RulesManager implements IRulesManager {
    
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
        Rule ruleToUpdate = findById(newState.getId());
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
    public List<Rule> findAll() {
        List rules = em.createNamedQuery("findAllRules").getResultList();
        return rules;
    }
}
