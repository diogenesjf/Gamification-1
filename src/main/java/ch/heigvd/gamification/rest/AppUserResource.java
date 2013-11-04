package ch.heigvd.gamification.rest;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.AppUser;
import ch.heigvd.gamification.model.Event;
import ch.heigvd.gamification.model.Success;
import ch.heigvd.gamification.services.crud.interfaces.IAppUsersManager;
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
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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
 * REST Service
 *
 * @author Alexandre Perusset
 */
@Stateless
@Path("users")
public class AppUserResource {

  @Context
  private UriInfo context;

  @EJB
  IAppUsersManager usersManager;

  @EJB
  IAppUsersTOService usersTOService;

  @EJB
  ISuccessesTOService successesTOService;
  
  @EJB
  IEventsTOService eventsTOService;

  public AppUserResource() {
  }

  /**
   *
   * @param newUserTO
   * @return
   */
  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  public Response createUser(AppUserTO newUserTO) {
    AppUser newUser = new AppUser();
    usersTOService.updateUserEntity(newUser, newUserTO);
    return Response.created(
            context.getAbsolutePathBuilder().path(Long.toString(
                            usersManager.create(newUser)
                    )).build()
    ).build();
  }

  /**
   *
   * @return
   */
  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public List<AppUserPublicTO> getAllUsers() {
    List<AppUserPublicTO> result = new LinkedList<>();
    for (AppUser user : usersManager.findAll()) {
      result.add(usersTOService.buildPublicUserTO(user));
    }
    return result;
  }

  /**
   *
   * @param id
   * @return
   * @throws EntityNotFoundException
   */
  @GET
  @Path("{id}")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public AppUserPublicTO getUser(@PathParam("id") long id) throws EntityNotFoundException {
    return usersTOService.buildPublicUserTO(usersManager.findById(id));
  }

  /**
   *
   * @param updatedUserTO
   * @param id
   * @return
   * @throws EntityNotFoundException
   */
  @PUT
  @Path("{id}")
  @Consumes({MediaType.APPLICATION_JSON})
  public Response updateUser(AppUserTO updatedUserTO, @PathParam("id") long id) throws EntityNotFoundException {
    AppUser userToUpdate = usersManager.findById(id);
    usersTOService.updateUserEntity(userToUpdate, updatedUserTO);
    usersManager.update(userToUpdate);
    return Response.ok().build();
  }

  /**
   *
   * @param id
   * @return
   * @throws EntityNotFoundException
   */
  @DELETE
  @Path("{id}")
  public Response deleteUser(@PathParam("id") long id) throws EntityNotFoundException {
    usersManager.delete(id);
    return Response.ok().build();
  }

  /**
   *
   * @param id
   * @return
   * @throws EntityNotFoundException
   */
  @GET
  @Path("{id}/success")
  public List<SuccessTO> getUserSuccesses(@PathParam("id") long id) throws EntityNotFoundException {
    //If we want another order, use a parametrized NamedQuery
    List<SuccessTO> successTO = new LinkedList<>();
    for (Success success : usersManager.findById(id).getSuccesses()) {
      successTO.add(successesTOService.buildSuccessTO(success));
    }
    return successTO;
  }
  
  /**
   *
   * @param id
   * @return
   * @throws EntityNotFoundException
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

}
