package br.com.funlife.gamification.services.exposed.interfaces;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.exceptions.UnauthorizedException;
import br.com.funlife.gamification.to.AppActionTO;
import java.util.List;
import javax.ejb.Remote;
import javax.ws.rs.core.Response;

/**
 * Interface for actions management. The methods inside can be called remotly or
 * with jax-rs rest api.
 *
 * @author Diogenes Firmiano
 */
@Remote
public interface IAppActionsResource {

  public long createAction(AppActionTO actionTO, long idApp) throws EntityNotFoundException, UnauthorizedException;

  public void updateAction(AppActionTO actionTO, long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

  public void deleteAction(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

  public List<AppActionTO> getActions(long idApp) throws EntityNotFoundException;

  public AppActionTO getAction(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

  public Response restCreateAction(AppActionTO actionTO, long idApp) throws EntityNotFoundException, UnauthorizedException;

  public Response restUpdateAction(AppActionTO actionTO, long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

  public Response restDeleteAction(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;
}
