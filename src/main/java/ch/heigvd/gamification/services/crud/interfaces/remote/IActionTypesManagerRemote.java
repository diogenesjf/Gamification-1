package ch.heigvd.gamification.services.crud.interfaces.remote;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.ActionType;
import ch.heigvd.gamification.model.Event;
import ch.heigvd.gamification.model.Rule;
import java.util.List;

/**
 *
 * @author Gaël Jobin
 */
public interface IActionTypesManagerRemote {

    long create(ActionType actionTypeData);

    void update(ActionType newState) throws EntityNotFoundException;

    void delete(long id) throws EntityNotFoundException;

    ActionType findById(long id) throws EntityNotFoundException;

    List<ActionType> findAll();

    List<Event> findAllActions();

    List<Rule> findAllRules();

}
