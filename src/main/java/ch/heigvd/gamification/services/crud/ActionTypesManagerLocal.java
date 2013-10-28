package ch.heigvd.gamification.services.crud;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.ActionType;
import ch.heigvd.gamification.model.Rule;
import ch.heigvd.gamification.model.UserAction;
import java.util.List;

/**
 *
 * @author Gaël JObin
 */
public interface ActionTypesManagerLocal {
    
    long create(ActionType actionTypeData);
    
    void update(ActionType newState) throws EntityNotFoundException;
    
    void delete(long id) throws EntityNotFoundException;
    
    ActionType findById(long id) throws EntityNotFoundException;
    
    List<ActionType> findAll();
    
    List<UserAction> findAllActions();
    
    List<Rule> findAllRules();
    
}
