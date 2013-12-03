package ch.heigvd.gamification.services.crud.interfaces.remote;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
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

    public long create(AppAction actionTypeData);

    public void update(AppAction newState) throws EntityNotFoundException;

    public void delete(long id) throws EntityNotFoundException;

    public AppAction findById(long id) throws EntityNotFoundException;

    public List<AppAction> findAll(Application application);

    public List<Event> findAllActions();

    public List<Rule> findAllRules();
    
    public void checkRights(long id, Application app) throws EntityNotFoundException, UnauthorizedException;
}
