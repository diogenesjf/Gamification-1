package ch.heigvd.gamification.services.crud.interfaces.remote;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Success;
import java.util.List;

/**
 *
 * @author Alexandre Perusset
 */
public interface ISuccessesManagerRemote {

    public long create(Success successData);

    public void update(Success newState, Application app) throws EntityNotFoundException, UnauthorizedException;

    public void delete(long id, Application app) throws EntityNotFoundException, UnauthorizedException;

    public Success findById(long id, Application app) throws EntityNotFoundException, UnauthorizedException;

    public List<Success> findAll(Application app);
    
    public void checkRights(Success success, Application app) throws UnauthorizedException;
}
