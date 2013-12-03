package ch.heigvd.gamification.services.crud.interfaces.remote;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Rule;
import java.util.List;

/**
 *
 * @author Gaël Jobin
 */
public interface IRulesManagerRemote {

    public long create(Rule employeeData);

    public void update(Rule newState) throws EntityNotFoundException;

    public void delete(long id) throws EntityNotFoundException;

    public Rule findById(long id) throws EntityNotFoundException;

    public List<Rule> findAll(Application application);
    
    public void checkRights(long id, Application app) throws EntityNotFoundException, UnauthorizedException;
}
