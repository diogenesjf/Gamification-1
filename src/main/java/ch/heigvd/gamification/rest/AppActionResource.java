package ch.heigvd.gamification.rest;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.model.AppAction;
import ch.heigvd.gamification.services.to.interfaces.IActionTypesTOService;
import ch.heigvd.gamification.to.AppActionTO;
import ch.heigvd.gamification.services.crud.interfaces.local.IAppActionsManagerLocal;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Service
 *
 * @author Gaël Jobin
 */
@Path("actions")
public class AppActionResource extends GamificationRESTResource {

  @EJB
  IAppActionsManagerLocal actionManager;

  @EJB
  IActionTypesTOService actionTOService;

  /**
   * Creates a new instance of AppActionResource
   */
  public AppActionResource() {
  }

  /**
   * Creates a new AppAction resource from the provided representation.
   *
   * @param actionTO the new action representation
   * @return Response HTTP Code 201 Created
   */
  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  public Response createAction(AppActionTO actionTO) {
    AppAction action = new AppAction();
    actionTOService.updateActionTypeEntity(action, actionTO);
    return Response.created(
            context.getAbsolutePathBuilder().path(Long.toString(
                            actionManager.create(action)
                    )).build()
    ).build();
  }

  /**
   * Retrieves a representation of a list of AppActionTO resources.
   *
   * @return List<AppActionTO> a list of AppActionTO
   * @throws EntityNotFoundException application does not exists
   */
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public List<AppActionTO> getActions() throws EntityNotFoundException {
    List<AppActionTO> result = new LinkedList<>();
    for (AppAction action : actionManager.findAll(getApplication())) {
      result.add(actionTOService.buildPublicActionTypeTO(action));
    }
    return result;
  }

  /**
   * Retrieves representation of an action resource.
   *
   * @param id id of the action
   * @return AppActionTO an instance of AppActionTO
   * @throws EntityNotFoundException action does not exists
   * @throws UnauthorizedException action does not belong to current application
   */
  @GET
  @Path("{id}")
  @Produces({MediaType.APPLICATION_JSON})
  public AppActionTO getAction(@PathParam("id") long id) throws EntityNotFoundException, UnauthorizedException {
    checkRights(id);
    return actionTOService.buildPublicActionTypeTO(actionManager.findById(id));
  }

  /**
   * Updates an action resource by passing his new representation.
   *
   * @param actionTO the new representation of the action
   * @param id id of the action to update
   * @return Response HTTP Code 204 No Content
   * @throws EntityNotFoundException if action does not exists
   * @throws UnauthorizedException action does not belong to current application
   */
  @PUT
  @Path("{id}")
  @Consumes({MediaType.APPLICATION_JSON})
  public Response updateResource(AppActionTO actionTO, @PathParam("id") long id) throws EntityNotFoundException, UnauthorizedException {
    checkRights(id);
    AppAction actionTypeToUpdate = actionManager.findById(id);
    actionTOService.updateActionTypeEntity(actionTypeToUpdate, actionTO);
    actionManager.update(actionTypeToUpdate);
    return Response.noContent().build();
  }

  /**
   * Deletes an actions resource.
   *
   * @param id the if of the action to delete
   * @return Response HTTP Code 204 No Content
   * @throws EntityNotFoundException if the action does not exists
   * @throws UnauthorizedException action does not belong current applicaiton
   */
  @DELETE
  @Path("{id}")
  public Response deleteResource(@PathParam("id") long id) throws EntityNotFoundException, UnauthorizedException {
    checkRights(id);
    actionManager.delete(id);
    return Response.noContent().build();
  }

  /**
   * Check if the passed action id belong to the current application.
   *
   * @param id the action id to check
   * @throws EntityNotFoundException action does not exists
   * @throws UnauthorizedException user action not belong to current application
   */
  @Override
  protected void checkRights(long id) throws EntityNotFoundException, UnauthorizedException {
    AppAction action = actionManager.findById(id);
    if (!action.getApplication().equals(getApplication())) {
      throw new UnauthorizedException();
    }
  }
}
