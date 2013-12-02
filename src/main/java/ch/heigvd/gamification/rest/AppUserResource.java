package ch.heigvd.gamification.rest;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.model.AppUser;
import ch.heigvd.gamification.model.Event;
import ch.heigvd.gamification.model.Success;
import ch.heigvd.gamification.services.crud.interfaces.local.IAppUsersManagerLocal;
import ch.heigvd.gamification.services.to.interfaces.IAppUsersTOService;
import ch.heigvd.gamification.services.to.interfaces.IEventsTOService;
import ch.heigvd.gamification.services.to.interfaces.ISuccessesTOService;
import ch.heigvd.gamification.to.AppUserPublicTO;
import ch.heigvd.gamification.to.AppUserTO;
import ch.heigvd.gamification.to.EventPublicTO;
import ch.heigvd.gamification.to.SuccessTO;
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
 * REST Service. Expose some services to get, add, update or delete users for an
 * existing application.
 *
 * @author Alexandre Perusset
 */
@Path("users")
public class AppUserResource extends GamificationRESTResource {

  @EJB
  IAppUsersManagerLocal usersManager;

  @EJB
  IAppUsersTOService usersTOService;

  @EJB
  ISuccessesTOService successesTOService;

  @EJB
  IEventsTOService eventsTOService;

  public AppUserResource() {
  }

  /**
   * Get the users of the application
   *
   * @return List<AppUserPublicTO> list of users
   * @throws EntityNotFoundException application not found
   */
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public List<AppUserPublicTO> getAllUsers() throws EntityNotFoundException {
    List<AppUserPublicTO> result = new LinkedList<>();
    for (AppUser user : usersManager.findAll(getApplication())) {
      result.add(usersTOService.buildPublicUserTO(user));
    }
    return result;
  }

  /**
   * Create a new user for the application.
   *
   * @param userTO the new user representation
   * @return Response HTTP Code 201 Created
   * @throws EntityNotFoundException application not found
   */
  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  public Response createUser(AppUserTO userTO) throws EntityNotFoundException {
    AppUser newUser = new AppUser();
    usersTOService.updateUserEntity(newUser, userTO, getApplication());
    return Response.created(
            context.getAbsolutePathBuilder().path(Long.toString(
                            usersManager.create(newUser)
                    )).build()
    ).build();
  }

  /**
   * Get informations of an user by his unique id.
   *
   * @param id the unique identifier of the user
   * @return AppUserPublicTO the user representation
   * @throws EntityNotFoundException user does not exists
   * @throws UnauthorizedException user does not belong to current application
   */
  @GET
  @Path("{id}")
  @Produces({MediaType.APPLICATION_JSON})
  public AppUserPublicTO getUser(@PathParam("id") long id) throws EntityNotFoundException, UnauthorizedException {
    checkRights(id);
    return usersTOService.buildPublicUserTO(usersManager.findById(id));
  }

  /**
   * Update a user by passing his new representation.
   *
   * @param userTO new user representation
   * @param id the identifier of the user to update
   * @return Response HTTP Code 204 No Content
   * @throws EntityNotFoundException user or application does not exists
   * @throws UnauthorizedException user does not belong to current application
   */
  @PUT
  @Path("{id}")
  @Consumes({MediaType.APPLICATION_JSON})
  public Response updateUser(AppUserTO userTO, @PathParam("id") long id) throws EntityNotFoundException, UnauthorizedException {
    checkRights(id);
    AppUser user = usersManager.findById(id);
    usersTOService.updateUserEntity(user, userTO, getApplication());
    usersManager.update(user);
    return Response.noContent().build();
  }

  /**
   * Delete a user of the application
   *
   * @param id the unique identifier of the user
   * @return Response HTTP Code 204 No Content
   * @throws EntityNotFoundException user does not exists
   * @throws UnauthorizedException user does not belong to current application
   */
  @DELETE
  @Path("{id}")
  public Response deleteUser(@PathParam("id") long id) throws EntityNotFoundException, UnauthorizedException {
    checkRights(id);
    usersManager.delete(id);
    return Response.noContent().build();
  }

  /**
   * Obtain all the successes (with no particular order) of the user by passing
   * his unique id.
   *
   * @param id the identifier of the user
   * @return List<SuccessTO> liste of obtained successes
   * @throws EntityNotFoundException user does not exists
   * @throws UnauthorizedException user does not belong to current application
   */
  @GET
  @Path("{id}/success")
  public List<SuccessTO> getUserSuccesses(@PathParam("id") long id) throws EntityNotFoundException, UnauthorizedException {
    checkRights(id);
    //If we want another order, use a parametrized NamedQuery
    List<SuccessTO> successTO = new LinkedList<>();
    for (Success success : usersManager.findById(id).getSuccesses()) {
      successTO.add(successesTOService.buildSuccessTO(success));
    }
    return successTO;
  }

  /**
   * Obtain all the events (with no particular order) of the user by passing his
   * unique id.
   *
   * @param id the identifier of the user
   * @return List<EventPublicTO> list of the user events
   * @throws EntityNotFoundException user does not exists
   */
  @GET
  @Path("{id}/events")
  public List<EventPublicTO> getUserEvents(@PathParam("id") long id) throws EntityNotFoundException {
    //If we want another order, use a parametrized NamedQuery
    List<EventPublicTO> eventsTO = new LinkedList<>();
    for (Event event : usersManager.findById(id).getEvents()) {
      eventsTO.add(eventsTOService.buildPublicEventTO(event));
    }
    return eventsTO;
  }

  /**
   * Check if the passed user id belong to the current application.
   *
   * @param id the user id to check
   * @throws EntityNotFoundException user does not exists
   * @throws UnauthorizedException user does not belong to current application
   */
  @Override
  protected void checkRights(long id) throws EntityNotFoundException, UnauthorizedException {
    AppUser user = usersManager.findById(id);
    if (!user.getApplication().equals(getApplication())) {
      throw new UnauthorizedException();
    }
  }
}
