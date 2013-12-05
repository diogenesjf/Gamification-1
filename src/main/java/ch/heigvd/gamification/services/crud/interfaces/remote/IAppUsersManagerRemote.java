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

    public void update(AppUser state, Application app) throws EntityNotFoundException, UnauthorizedException;

    public void delete(long id, Application app) throws EntityNotFoundException, UnauthorizedException;

    public AppUser findById(long id, Application app) throws EntityNotFoundException, UnauthorizedException;

    public List<AppUser> findAll(Application app);

    public List<AppUser> findAllBySuccess(Success success, Application app) throws UnauthorizedException;
    
    public void checkRights(AppUser user, Application app) throws UnauthorizedException;
    
    public List<Success> checkForNewSuccesses(AppUser user);
}
