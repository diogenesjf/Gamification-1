package br.com.funlife.gamification.services.exposed;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.services.crud.interfaces.IApplicationsManager;
import br.com.funlife.gamification.services.exposed.interfaces.IApplicationsResource;
import br.com.funlife.gamification.services.to.interfaces.IApplicationsTOService;
import br.com.funlife.gamification.to.ApplicationTO;
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
 * REST and Remote Service. Expose some services for applications management.
 *
 * @author Diogenes Firmiano
 */
@Stateless
@Path("applications")
public class ApplicationsResource implements IApplicationsResource {

  @Context
  protected UriInfo context;

  @EJB
  private IApplicationsTOService applicationTOService;

  @EJB
  private IApplicationsManager applicationManager;

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
  @Override
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
  @Override
  public Response restCreateApplication(ApplicationTO applicationTO) {
    return Response.created(
            context.getAbsolutePathBuilder().path(Long.toString(
                            createApplication(applicationTO)
                    )).build()
    ).build();
  }

  @Override
  public long createApplication(ApplicationTO applicationTO) {
    Application newApplication = new Application();
    applicationTOService.updateApplicationEntity(newApplication, applicationTO);
    return applicationManager.create(newApplication);
  }

  /**
   * Retrieve the application with the specified id.
   *
   * @param id id of the application
   * @return ApplicationTO the application, if found
   * @throws EntityNotFoundException if the application does not exists
   */
  @GET
  @Path("{id}")
  @Produces({MediaType.APPLICATION_JSON})
  @Override
  public ApplicationTO getApplication(@PathParam("id") long id) throws EntityNotFoundException {
    return applicationTOService.buildPublicApplicationTO(applicationManager.findById(id));
  }

  /**
   * Update an application with a new representation.
   *
   * @param applicationTO the new representation
   * @param id the id of the application
   * @return Response HTTP Code 204 No Content
   * @throws EntityNotFoundException if the application does not exist
   */
  @PUT
  @Path("{id}")
  @Consumes({MediaType.APPLICATION_JSON})
  @Override
  public Response restUpdateApplication(ApplicationTO applicationTO, @PathParam("id") long id) throws EntityNotFoundException {
    updateApplication(applicationTO, id);
    return Response.noContent().build();
  }

  @Override
  public void updateApplication(ApplicationTO applicationTO, long id) throws EntityNotFoundException {
    Application appToUpdate = applicationManager.findById(id);
    applicationTOService.updateApplicationEntity(appToUpdate, applicationTO);
    applicationManager.update(appToUpdate);
  }

  /**
   * Delete an application by passing his id.
   *
   * @param id the id of the application to delete
   * @return Response HTTP Code 204 No Content
   * @throws EntityNotFoundException if the application does not exists
   */
  @DELETE
  @Path("{id}")
  @Override
  public Response restDeleteApplication(@PathParam("id") long id) throws EntityNotFoundException {
    deleteApplication(id);
    return Response.noContent().build();
  }

  @Override
  public void deleteApplication(long id) throws EntityNotFoundException {
    applicationManager.delete(id);
  }

    //TODO get all elements ?
}
