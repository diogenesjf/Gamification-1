package ch.heigvd.gamification.services.crud.interfaces.remote;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.model.AppUser;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Success;
import java.util.List;

/**
 *
 * @author Alexandre perusset
 */
public interface IAppUsersManagerRemote {

    public long create(AppUser user);

    public void update(AppUser state) throws EntityNotFoundException;

    public void delete(long id) throws EntityNotFoundException;

    public AppUser findById(long id) throws EntityNotFoundException;

    public List<AppUser> findAll(Application application);

    public List<AppUser> findAllBySuccess(long id);
    
    public void checkRights(long id, Application app) throws EntityNotFoundException, UnauthorizedException;
    
    public List<Success> checkForNewSuccesses(AppUser user);
}
