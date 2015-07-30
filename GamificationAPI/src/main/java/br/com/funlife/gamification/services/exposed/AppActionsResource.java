package br.com.funlife.gamification.services.exposed;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.exceptions.UnauthorizedException;
import br.com.funlife.gamification.model.AppAction;
import br.com.funlife.gamification.model.AppActionPoint;
import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.services.crud.interfaces.IAppActionPointsManager;
import br.com.funlife.gamification.services.crud.interfaces.IAppActionsManager;
import br.com.funlife.gamification.services.crud.interfaces.IApplicationsManager;
import br.com.funlife.gamification.services.exposed.interfaces.IAppActionsResource;
import br.com.funlife.gamification.services.to.interfaces.IAppActionPointsTOService;
import br.com.funlife.gamification.services.to.interfaces.IAppActionsTOService;
import br.com.funlife.gamification.to.AppActionPointTO;
import br.com.funlife.gamification.to.AppActionTO;
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
 * REST and Remote Service. Expose some service to manage application actions.
 *
 * @author Diogenes Firmiano
 */
@Stateless
@Path("actions")
public class AppActionsResource implements IAppActionsResource {

  @Context
  private UriInfo context;

  @EJB
  private IAppActionsManager actionManager;

  @EJB
  private IAppActionPointsManager actionPointManager;

  @EJB
  private IAppActionsTOService actionTOService;

  @EJB
  private IAppActionPointsTOService actionPointTOService;

  @EJB
  private IApplicationsManager appManager;

  /**
   * Creates a new instance of AppActionResource
   */
  public AppActionsResource() {
  }

  /**
   * Create a new AppAction resource from the provided representation. This
   * method is used by jax-rs when th api user call the api via rest/jax-rs.
   *
   * @param actionTO the new action representation
   * @param idApp id of the application
   * @return Response HTTP Code 201 Created
   * @throws EntityNotFoundException if application does not exists
   * @throws br.com.funlife.gamification.exceptions.UnauthorizedException
   */
  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  @Override
  public Response restCreateAction(AppActionTO actionTO, @HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException, UnauthorizedException {
    return Response.created(
            context.getAbsolutePathBuilder().path(Long.toString(
                            createAction(actionTO, idApp)
                    )).build()
    ).build();
  }

  /**
   * Create a new AppAction resource from the provided representation. This
   * method
   *
   * @param actionTO the new action representation
   * @param idApp id of the application
   * @return id of the new action
   * @throws EntityNotFoundException if application does not exists
   * @throws br.com.funlife.gamification.exceptions.UnauthorizedException
   */
  @Override
  public long createAction(AppActionTO actionTO, long idApp) throws EntityNotFoundException, UnauthorizedException {
    AppAction action = new AppAction();
    actionTOService.updateActionTypeEntity(action, actionTO, appManager.findById(idApp));
    long actionId = actionManager.create(action);
    
    if (actionTO.getPointsList()!=null && actionTO.getPointsList().size()>0) {
        for (AppActionPointTO actionPointTO : actionTO.getPointsList()) {
            AppActionPoint actionPoint = new AppActionPoint();
            actionPointTO.setActionID(actionId);
            actionPointTOService.updateActionPointTypeEntity(actionPoint, actionPointTO, appManager.findById(idApp));
            actionPointManager.create(actionPoint);
        }
    }
    
    return actionId;
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
  public Response restUpdateAction(AppActionTO actionTO, @PathParam("id") long id, @HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException, UnauthorizedException {
    updateAction(actionTO, id, idApp);
    return Response.noContent().build();
  }

  @Override
  public void updateAction(AppActionTO actionTO, long id, long idApp) throws EntityNotFoundException, UnauthorizedException {
    Application app = appManager.findById(idApp);
    AppAction action = actionManager.findById(id, app);
    actionTOService.updateActionTypeEntity(action, actionTO, app);
    actionManager.update(action, app);
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
  public Response restDeleteAction(@PathParam("id") long id, @HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException, UnauthorizedException {
    deleteAction(id, idApp);
    return Response.noContent().build();
  }

  @Override
  public void deleteAction(long id, long idApp) throws EntityNotFoundException, UnauthorizedException {
    actionManager.delete(id, appManager.findById(idApp));
  }
}
