package br.com.funlife.gamification.services.exposed.interfaces;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.exceptions.UnauthorizedException;
import br.com.funlife.gamification.to.AppUserPublicTO;
import br.com.funlife.gamification.to.AppUserTO;
import br.com.funlife.gamification.to.EventTO;
import br.com.funlife.gamification.to.SuccessTO;
import java.util.List;
import javax.ejb.Remote;
import javax.ws.rs.core.Response;

/**
 * Interface for user management. The methods inside can be called remotly or
 * with jax-rs rest api.
 *
 * @author Diogenes Firmiano
 */
@Remote
public interface IAppUsersResource {

  public Long createUser(AppUserTO userTO, Long idApp) throws EntityNotFoundException;

  public void updateUser(AppUserTO userTO, Long id, Long idApp) throws EntityNotFoundException, UnauthorizedException;

  public void deleteUser(Long id, Long idApp) throws EntityNotFoundException, UnauthorizedException;

  public List<AppUserPublicTO> getAllUsers(Long idApp) throws EntityNotFoundException;

  public AppUserPublicTO getUser(Long id, Long idApp) throws EntityNotFoundException, UnauthorizedException;

  public List<SuccessTO> getUserSuccesses(Long id, Long idApp) throws EntityNotFoundException, UnauthorizedException;

  public List<EventTO> getUserEvents(Long id, Long idApp) throws EntityNotFoundException, UnauthorizedException;

  public Response restCreateUser(AppUserTO userTO, Long idApp) throws EntityNotFoundException;

  public Response restUpdateUser(AppUserTO userTO, Long id, Long idApp) throws EntityNotFoundException, UnauthorizedException;

  public Response restDeleteUser(Long id, Long idApp) throws EntityNotFoundException, UnauthorizedException;
}
