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

    public void update(Success newState) throws EntityNotFoundException;

    public void delete(long id) throws EntityNotFoundException;

    public Success findById(long id) throws EntityNotFoundException;

    public List<Success> findAll(Application application);

    public List<Success> findAllAcquiredByUser(long id);
    
    public void checkRights(long id, Application app) throws EntityNotFoundException, UnauthorizedException;
}
