package ch.heigvd.gamification.services.exposed;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.model.AppAction;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.services.crud.interfaces.IAppActionsManager;
import ch.heigvd.gamification.services.crud.interfaces.IApplicationsManager;
import ch.heigvd.gamification.services.exposed.interfaces.IAppActionsResource;
import ch.heigvd.gamification.services.to.interfaces.IAppActionsTOService;
import ch.heigvd.gamification.to.AppActionTO;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * REST Service. Expose some service to manage application actions.
 *
 * @author Gaël Jobin
 */
@Stateless
@Path("actions")
public class AppActionsResource implements IAppActionsResource {

  @Context
  private UriInfo context;
  
  @EJB
  private IAppActionsManager actionManager;

  @EJB
  private IAppActionsTOService actionTOService;
  
  @EJB
  private IApplicationsManager appManager;

  /**
   * Creates a new instance of AppActionResource
   */
  public AppActionsResource() {
  }

  /**
   * Creates a new AppAction resource from the provided representation.
   *
   * @param actionTO the new action representation
   * @param idApp id of the application
   * @return Response HTTP Code 201 Created
   * @throws EntityNotFoundException if application does not exists
   */
  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  @Override
  public Response createAction(AppActionTO actionTO, @HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException {
    AppAction action = new AppAction();
    actionTOService.updateActionTypeEntity(action, actionTO, appManager.findById(idApp));
    return Response.created(
            context.getAbsolutePathBuilder().path(Long.toString(
                            actionManager.create(action)
                    )).build()
    ).build();
  }

  /**
   * Retrieves a representation of a list of AppActionTO resources.
   *
   * @param idApp id of the application
   * @return List<AppActionTO> a list of AppActionTO
   * @throws EntityNotFoundException application does not exists
   */
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  @Override
  public List<AppActionTO> getActions(@HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException {
    List<AppActionTO> result = new LinkedList<>();
    for (AppAction action : actionManager.findAll(appManager.findById(idApp))) {
      result.add(actionTOService.buildPublicActionTypeTO(action));
    }
    return result;
  }

  /**
   * Retrieves representation of an action resource.
   *
   * @param id id of the action
   * @param idApp id of the application
   * @return AppActionTO an instance of AppActionTO
   * @throws EntityNotFoundException action does not exists
   * @throws UnauthorizedException action does not belong to application
   */
  @GET
  @Path("{id}")
  @Produces({MediaType.APPLICATION_JSON})
  @Override
  public AppActionTO getAction(@PathParam("id") long id, @HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException, UnauthorizedException {
    return actionTOService.buildPublicActionTypeTO(actionManager.findById(id, appManager.findById(idApp)));
  }

  /**
   * Updates an action resource by passing his new representation.
   *
   * @param actionTO the new representation of the action
   * @param id id of the action to update
   * @param idApp id of the application
   * @return Response HTTP Code 204 No Content
   * @throws EntityNotFoundException if action or application does not exists
   * @throws UnauthorizedException action does not belong to application
   */
  @PUT
  @Path("{id}")
  @Consumes({MediaType.APPLICATION_JSON})
  @Override
  public Response updateAction(AppActionTO actionTO, @PathParam("id") long id, @HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException, UnauthorizedException {
    Application app = appManager.findById(idApp);
    AppAction action = actionManager.findById(id, app);
    actionTOService.updateActionTypeEntity(action, actionTO, app);
    actionManager.update(action, app);
    return Response.noContent().build();
  }

  /**
   * Deletes an actions resource.
   *
   * @param id the if of the action to delete
   * @param idApp id of the application
   * @return Response HTTP Code 204 No Content
   * @throws EntityNotFoundException if the action does not exists
   * @throws UnauthorizedException action does not belong application
   */
  @DELETE
  @Path("{id}")
  @Override
  public Response deleteAction(@PathParam("id") long id, @HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException, UnauthorizedException {
    actionManager.delete(id, appManager.findById(idApp));
    return Response.noContent().build();
  }
}
