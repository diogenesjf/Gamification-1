package ch.heigvd.gamification.rest;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.AppUser;
import ch.heigvd.gamification.services.crud.interfaces.IAppUsersManager;
import ch.heigvd.gamification.services.to.interfaces.IAppUsersTOService;
import ch.heigvd.gamification.to.AppUserTO;
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

  public AppUserResource() {
  }

  @POST
  @Consumes({"application/json"})
  public Response createRessource(AppUserTO newUserTO) {
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
  public List<AppUserTO> getResourceList() {
    List<AppUserTO> result = new LinkedList<>();
    for (AppUser user : usersManager.findAll()) {
      result.add(usersTOService.buildUserTO(user));
    }
    return result;
  }
  
  @GET
  @Path("{id}")
  @Produces({"application/json", "application/xml"})
  public AppUserTO getResource(@PathParam("id") long id) throws EntityNotFoundException {
    return usersTOService.buildUserTO(usersManager.findById(id));
  }
  
  @PUT
  @Path("{id}")
  @Consumes({"application/json"})
  public Response updateResource(AppUserTO updatedUserTO, @PathParam("id") long id) throws EntityNotFoundException {
    AppUser userToUpdate = usersManager.findById(id);
    usersTOService.updateUserEntity(userToUpdate, updatedUserTO);
    usersManager.update(userToUpdate);
    return Response.ok().build();
  }
  
  @DELETE
  @Path("{id}")
  public Response deleteResource(@PathParam("id") long id) throws EntityNotFoundException {
    usersManager.delete(id);
    return Response.ok().build();
  }
  
}
