package ch.heigvd.gamification.services.crud.interfaces.remote;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.AppUser;
import ch.heigvd.gamification.model.Application;
import java.util.List;

/**
 *
 * @author Alexandre perusset
 */
public interface IAppUsersManagerRemote {

    public long create(AppUser userData);

    public void update(AppUser newState) throws EntityNotFoundException;

    public void delete(long id) throws EntityNotFoundException;

    public AppUser findById(long id) throws EntityNotFoundException;

    public List<AppUser> findAll(Application application);

    public List<AppUser> findAllBySuccess(long id, Application application);

}
