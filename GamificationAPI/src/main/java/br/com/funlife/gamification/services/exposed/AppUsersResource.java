package br.com.funlife.gamification.services.exposed;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.exceptions.UnauthorizedException;
import br.com.funlife.gamification.model.AppUser;
import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.model.Event;
import br.com.funlife.gamification.model.Success;
import br.com.funlife.gamification.services.crud.interfaces.IAppUsersManager;
import br.com.funlife.gamification.services.crud.interfaces.IApplicationsManager;
import br.com.funlife.gamification.services.exposed.interfaces.IAppUsersResource;
import br.com.funlife.gamification.services.to.interfaces.IAppUsersTOService;
import br.com.funlife.gamification.services.to.interfaces.IEventsTOService;
import br.com.funlife.gamification.services.to.interfaces.ISuccessesTOService;
import br.com.funlife.gamification.to.AppUserPublicTO;
import br.com.funlife.gamification.to.AppUserTO;
import br.com.funlife.gamification.to.EventTO;
import br.com.funlife.gamification.to.SuccessTO;
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
 * REST and Remote Service. Expose some services to manage users of an
 * application.
 *
 * @author Diogenes Firmiano
 */
@Stateless
@Path("users")
public class AppUsersResource implements IAppUsersResource {

  @Context
  private UriInfo context;

  @EJB
  private IAppUsersManager usersManager;

  @EJB
  private IAppUsersTOService usersTOService;

  @EJB
  private ISuccessesTOService successesTOService;

  @EJB
  private IEventsTOService eventsTOService;

  @EJB
  private IApplicationsManager appManager;

  public AppUsersResource() {
  }

  /**
   * Get the users of the application
   *
   * @param idApp id of the application
   * @return List<AppUserPublicTO> list of users
   * @throws EntityNotFoundException application not found
   */
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  @Override
  public List<AppUserPublicTO> getAllUsers(@HeaderParam(value = RESTAPI.APP) Long idApp) throws EntityNotFoundException {
    List<AppUserPublicTO> result = new LinkedList<>();
    for (AppUser user : usersManager.findAll(appManager.findById(idApp))) {
      result.add(usersTOService.buildPublicUserTO(user));
    }
    return result;
  }

  /**
   * Create a new user for the application.
   *
   * @param userTO the new user representation
   * @param idApp id of the application
   * @return Response HTTP Code 201 Created
   * @throws EntityNotFoundException application not found
   */
  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  @Override
  public Response restCreateUser(AppUserTO userTO, @HeaderParam(value = RESTAPI.APP) Long idApp) throws EntityNotFoundException {
    return Response.created( 
            context.getAbsolutePathBuilder().path(Long.toString(
                            createUser(userTO, idApp)
                    )).build()
    ).build();
  }

  @Override
  public Long createUser(AppUserTO userTO,Long idApp) throws EntityNotFoundException {
    AppUser newUser = new AppUser();
    usersTOService.updateUserEntity(newUser, userTO, appManager.findById(idApp));
    return usersManager.create(newUser);
  }

  /**
   * Get informations of an user by his unique id.
   *
   * @param id the unique identifier of the user
   * @param idApp id of the application
   * @return AppUserPublicTO the user representation
   * @throws EntityNotFoundException user does not exists
   * @throws UnauthorizedException user does not beLong to application
   */
  @GET
  @Path("{id}")
  @Produces({MediaType.APPLICATION_JSON})
  @Override
  public AppUserPublicTO getUser(@PathParam("id") Long id, @HeaderParam(value = RESTAPI.APP) Long idApp) throws EntityNotFoundException, UnauthorizedException {
    return usersTOService.buildPublicUserTO(usersManager.findById(id, appManager.findById(idApp)));
  }

  /**
   * Update a user by passing his new representation.
   *
   * @param userTO new user representation
   * @param id the identifier of the user to update
   * @param idApp id of the application
   * @return Response HTTP Code 204 No Content
   * @throws EntityNotFoundException user or application does not exists
   * @throws UnauthorizedException user does not beLong to application
   */
  @PUT
  @Path("{id}")
  @Consumes({MediaType.APPLICATION_JSON})
  @Override
  public Response restUpdateUser(AppUserTO userTO, @PathParam("id") Long id, @HeaderParam(value = RESTAPI.APP) Long idApp) throws EntityNotFoundException, UnauthorizedException {
    updateUser(userTO, id, idApp);
    return Response.noContent().build();
  }

  @Override
  public void updateUser(AppUserTO userTO, Long id, Long idApp) throws EntityNotFoundException, UnauthorizedException {
    Application app = appManager.findById(idApp);
    AppUser user = usersManager.findById(id, app);
    usersTOService.updateUserEntity(user, userTO, app);
    usersManager.update(user, app);
  }

  /**
   * Delete a user of the application
   *
   * @param id the unique identifier of the user
   * @param idApp id of the application
   * @return Response HTTP Code 204 No Content
   * @throws EntityNotFoundException user does not exists
   * @throws UnauthorizedException user does not beLong to application
   */
  @DELETE
  @Path("{id}")
  @Override
  public Response restDeleteUser(@PathParam("id") Long id, @HeaderParam(value = RESTAPI.APP) Long idApp) throws EntityNotFoundException, UnauthorizedException {
    deleteUser(id, idApp);
    return Response.noContent().build();
  }

  @Override
  public void deleteUser(Long id, Long idApp) throws EntityNotFoundException, UnauthorizedException {
    usersManager.delete(id, appManager.findById(idApp));
  }

  /**
   * Obtain all the successes (with no particular order) of the user by passing
   * his unique id.
   *
   * @param id the identifier of the user
   * @param idApp id of the application
   * @return List<SuccessTO> liste of obtained successes
   * @throws EntityNotFoundException user does not exists
   * @throws UnauthorizedException user does not beLong to application
   */
  @GET
  @Path("{id}/successes")
  @Produces({MediaType.APPLICATION_JSON})
  @Override
  public List<SuccessTO> getUserSuccesses(@PathParam("id") Long id, @HeaderParam(value = RESTAPI.APP) Long idApp) throws EntityNotFoundException, UnauthorizedException {
    //If we want another order, use a parametrized NamedQuery
    List<SuccessTO> successTO = new LinkedList<>();
    for (Success success : usersManager.findById(id, appManager.findById(idApp)).getSuccesses()) {
      successTO.add(successesTOService.buildSuccessTO(success));
    }
    return successTO;
  }

  /**
   * Obtain all the events (with no particular order) of the user by passing his
   * unique id.
   *
   * @param id the identifier of the user
   * @param idApp id of the application
   * @return List<EventPublicTO> list of the user events
   * @throws EntityNotFoundException user does not exists
   * @throws UnauthorizedException user does not beLong to application
   */
  @GET
  @Path("{id}/events")
  @Produces({MediaType.APPLICATION_JSON})
  @Override
  public List<EventTO> getUserEvents(@PathParam("id") Long id, @HeaderParam(value = RESTAPI.APP) Long idApp) throws EntityNotFoundException, UnauthorizedException {
    //If we want another order, use a parametrized NamedQuery
    List<EventTO> eventsTO = new LinkedList<>();
    for (Event event : usersManager.findById(id, appManager.findById(idApp)).getEvents()) {
      eventsTO.add(eventsTOService.buildEventTO(event));
    }
    return eventsTO;
  }
}
