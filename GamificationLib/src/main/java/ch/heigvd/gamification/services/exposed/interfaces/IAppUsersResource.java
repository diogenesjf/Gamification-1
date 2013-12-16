package ch.heigvd.gamification.services.exposed.interfaces;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.to.AppUserPublicTO;
import ch.heigvd.gamification.to.AppUserTO;
import ch.heigvd.gamification.to.EventTO;
import ch.heigvd.gamification.to.SuccessTO;
import java.util.List;
import javax.ejb.Remote;
import javax.ws.rs.core.Response;

/**
 *
 * @author Alexandre Perusset
 */
@Remote
public interface IAppUsersResource {

    public long createUser(AppUserTO userTO, long idApp) throws EntityNotFoundException;
    
    public void updateUser(AppUserTO userTO, long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

    public void deleteUser(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

    public List<AppUserPublicTO> getAllUsers(long idApp) throws EntityNotFoundException;

    public AppUserPublicTO getUser(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

    public List<SuccessTO> getUserSuccesses(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

    public List<EventTO> getUserEvents(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;
    
    public Response restCreateUser(AppUserTO userTO, long idApp) throws EntityNotFoundException;

    public Response restUpdateUser(AppUserTO userTO, long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

    public Response restDeleteUser(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;
}
