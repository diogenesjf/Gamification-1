package ch.heigvd.gamification.rest;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.AppUser;
import ch.heigvd.gamification.model.Success;
import ch.heigvd.gamification.model.UserAction;
import ch.heigvd.gamification.services.crud.interfaces.IAppUsersManager;
import ch.heigvd.gamification.services.to.interfaces.IAppUsersTOService;
import ch.heigvd.gamification.services.to.interfaces.ISuccessTOService;
import ch.heigvd.gamification.services.to.interfaces.IUserActionsTOService;
import ch.heigvd.gamification.to.AppUserTO;
import ch.heigvd.gamification.to.SuccessTO;
import ch.heigvd.gamification.to.UserActionTO;
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
  ISuccessTOService successTOService;

  @EJB
  IUserActionsTOService userActionTOService;

  public AppUserResource() {
  }

  @POST
  @Consumes({"application/json"})
  public Response createUser(AppUserTO newUserTO) {
    AppUser newUser = new AppUser();
    usersTOService.updateUserEntity(newUser, newUserTO);
    return Response.created(
            context.getAbsolutePathBuilder().path(Long.toString(
                            usersManager.create(newUser)
                    )).build()
    ).build();
  }

  @GET
  @Produces({"application/json", "application/xml"})
  public List<AppUserTO> getAllUsers() {
    List<AppUserTO> result = new LinkedList<>();
    for (AppUser user : usersManager.findAll()) {
      result.add(usersTOService.buildUserTO(user));
    }
    return result;
  }

  @GET
  @Path("{id}")
  @Produces({"application/json", "application/xml"})
  public AppUserTO getUser(@PathParam("id") long id) throws EntityNotFoundException {
    return usersTOService.buildUserTO(usersManager.findById(id));
  }

  @PUT
  @Path("{id}")
  @Consumes({"application/json"})
  public Response updateUser(AppUserTO updatedUserTO, @PathParam("id") long id) throws EntityNotFoundException {
    AppUser userToUpdate = usersManager.findById(id);
    usersTOService.updateUserEntity(userToUpdate, updatedUserTO);
    usersManager.update(userToUpdate);
    return Response.ok().build();
  }

  @DELETE
  @Path("{id}")
  public Response deleteUser(@PathParam("id") long id) throws EntityNotFoundException {
    usersManager.delete(id);
    return Response.ok().build();
  }

  @GET
  @Path("{id}/success")
  public List<SuccessTO> getUserSuccess(@PathParam("id") long id) throws EntityNotFoundException {
    //If we want another order, use a parametrized NamedQuery
    List<SuccessTO> successTO = new LinkedList<>();
    for (Success success : usersManager.findById(id).getSuccess()) {
      successTO.add(successTOService.buildSuccessTO(success));
    }
    return successTO;
  }

  @GET
  @Path("{id}/actions")
  public List<UserActionTO> getUserActions(@PathParam("id") long id) throws EntityNotFoundException {
    //If we want another order, use a parametrized NamedQuery
    List<UserActionTO> userActionTO = new LinkedList<>();
    for (UserAction userAction : usersManager.findById(id).getActions()) {
      userActionTO.add(userActionTOService.buildUserActionTO(userAction));
    }
    return userActionTO;
  }

}
