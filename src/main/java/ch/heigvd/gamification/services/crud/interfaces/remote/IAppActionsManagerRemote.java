package ch.heigvd.gamification.services.crud.interfaces.remote;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.AppAction;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Event;
import ch.heigvd.gamification.model.Rule;
import java.util.List;

/**
 *
 * @author Gaël Jobin
 */
public interface IAppActionsManagerRemote {

    long create(AppAction actionTypeData);

    void update(AppAction newState) throws EntityNotFoundException;

    void delete(long id) throws EntityNotFoundException;

    AppAction findById(long id) throws EntityNotFoundException;

    List<AppAction> findAll(Application application);

    List<Event> findAllActions();

    List<Rule> findAllRules();

}
