package ch.heigvd.gamification.services.exposed.interfaces;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.to.AppActionTO;
import java.util.List;
import javax.ejb.Remote;
import javax.ws.rs.core.Response;

/**
 *
 * @author Alexandre Perusset
 */
@Remote
public interface IAppActionsResource {

    public long createAction(AppActionTO actionTO, long idApp) throws EntityNotFoundException;
    
    public void updateAction(AppActionTO actionTO, long id, long idApp) throws EntityNotFoundException, UnauthorizedException;
    
    public void deleteAction(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

    public Response restCreateAction(AppActionTO actionTO, long idApp) throws EntityNotFoundException;

    public List<AppActionTO> getActions(long idApp) throws EntityNotFoundException;

    public AppActionTO getAction(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

    public Response restUpdateAction(AppActionTO actionTO, long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

    public Response restDeleteAction(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;
}
