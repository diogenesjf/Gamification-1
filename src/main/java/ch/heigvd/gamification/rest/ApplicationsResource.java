package ch.heigvd.gamification.rest;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.services.crud.interfaces.local.IApplicationsManagerLocal;
import ch.heigvd.gamification.services.to.interfaces.IApplicationsTOService;
import ch.heigvd.gamification.to.ApplicationTO;
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
 * REST Service. Expose some services for applications management.
 * 
 * @author Gaël Jobin
 */
@Path("applications")
public class ApplicationsResource extends GamificationRESTResource {

  @EJB
  private IApplicationsTOService applicationTOService;

  @EJB
  private IApplicationsManagerLocal applicationManager;

  /**
   * Creates a new instance of ApplicationResource
   */
  public ApplicationsResource() {
  }

  /**
   * Retrieves a representation of a list of Application resources
   *
   * @return List<ApplicationTO> a list of ApplicationTO
   */
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public List<ApplicationTO> getApplications() {
    List<ApplicationTO> result = new LinkedList<>();
    for (Application application : applicationManager.findAll()) {
      result.add(applicationTOService.buildPublicApplicationTO(application));
    }
    return result;
  }

  /**
   * Creates a new Application resource from the provided representation
   *
   * @param applicationTO the application to create
   * @return Response HTTP Code 201 Created
   */
  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  public Response createApplication(ApplicationTO applicationTO) {
    Application newApplication = new Application();
    applicationTOService.updateApplicationEntity(newApplication, applicationTO);
    return Response.created(
            context.getAbsolutePathBuilder().path(Long.toString(
                            applicationManager.create(newApplication)
                    )).build()
    ).build();
  }
  
  /**
   * Retrieve the application with the specified id.
   * @param id id of the application
   * @return ApplicationTO the application, if found
   * @throws EntityNotFoundException if the application does not exists
   */
  @GET
  @Path("{id}")
  @Produces({MediaType.APPLICATION_JSON})
  public ApplicationTO getApplication(@PathParam("id") long id) throws EntityNotFoundException {
    return applicationTOService.buildPublicApplicationTO(applicationManager.findById(id));
  }
  
  /**
   * Update an application with a new representation.
   * @param applicationTO the new representation
   * @param id the id of the application
   * @return Response HTTP Code 204 No Content
   * @throws EntityNotFoundException if the application does not exist
   */
  @PUT
  @Path("{id}")
  @Consumes({MediaType.APPLICATION_JSON})
  public Response updateApplication(ApplicationTO applicationTO, @PathParam("id") long id) throws EntityNotFoundException {
    Application appToUpdate = applicationManager.findById(id);
    applicationTOService.updateApplicationEntity(appToUpdate, applicationTO);
    applicationManager.update(appToUpdate);
    return Response.noContent().build();
  }
  
  /**
   * Delete an application by passing his id.
   * @param id the id of the application to delete
   * @return Response HTTP Code 204 No Content
   * @throws EntityNotFoundException  if the application does not exists
   */
  @DELETE
  @Path("{id}")
  public Response deleteApplication(@PathParam("id") long id) throws EntityNotFoundException {
    applicationManager.delete(id);
    return Response.noContent().build();
  }
  
  //TODO get all elements ? later ...
}
