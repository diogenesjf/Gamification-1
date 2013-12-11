package ch.heigvd.gamification.services.exposed.interfaces;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.to.AppUserPublicTO;
import ch.heigvd.gamification.to.AppUserTO;
import ch.heigvd.gamification.to.EventPublicTO;
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
  
  public List<AppUserPublicTO> getAllUsers(long idApp) throws EntityNotFoundException;
  
  public Response createUser(AppUserTO userTO, long idApp) throws EntityNotFoundException;
  
  public AppUserPublicTO getUser(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;
  
  public Response updateUser(AppUserTO userTO, long id, long idApp) throws EntityNotFoundException, UnauthorizedException;
  
  public Response deleteUser(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;
  
  public List<SuccessTO> getUserSuccesses(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;
  
  public List<EventPublicTO> getUserEvents(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;
}
