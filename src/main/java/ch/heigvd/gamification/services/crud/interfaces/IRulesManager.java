package ch.heigvd.gamification.services.crud.interfaces;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Rule;
import ch.heigvd.gamification.model.Success;
import java.util.List;

/**
 *
 * @author Gaël Jobin
 */
public interface IRulesManager {
    long create(Rule employeeData);
    
    void update(Rule newState) throws EntityNotFoundException;
    
    void delete(long id) throws EntityNotFoundException;
    
    Rule findById(long id) throws EntityNotFoundException;
    
    List<Rule> findAll();
    
    List<Success> findAllSuccess();
}
