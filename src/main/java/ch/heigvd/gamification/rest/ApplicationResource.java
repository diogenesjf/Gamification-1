package ch.heigvd.gamification.rest;

import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.services.crud.interfaces.IApplicationsManager;
import ch.heigvd.gamification.services.to.interfaces.IApplicationsTOService;
import ch.heigvd.gamification.to.PublicApplicationTO;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Gaël Jobin
 */
@Stateless
@Path("applications")
public class ApplicationResource extends GamificationRESTResource {
        
    @EJB
    private IApplicationsTOService applicationTOService;
    
    @EJB
    private IApplicationsManager applicationsManager;
    
    /**
     * Creates a new instance of ApplicationResource
     */
    public ApplicationResource() {
    }
    
    /**
     * Creates a new Application resource from the provided representation
     * @return an instance of PublicApplicationTO
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createResource(PublicApplicationTO newApplicationTO) {
        Application newApplication = new Application();
        applicationTOService.updateApplicationEntity(newApplication,newApplicationTO);
        long newApplicationId = applicationsManager.create(newApplication);
        URI createdURI = context.getAbsolutePathBuilder().path(Long.toString(newApplicationId)).build();
        return Response.created(createdURI).build();
    }
    
    /**
     * Retrieves a representation of a list of Application resources
     * @return an instance of ApplicationTO
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<PublicApplicationTO> getResources() {
        List<Application> applications = applicationsManager.findAll();
        List<PublicApplicationTO> result = new LinkedList<PublicApplicationTO>();
        for(Application application : applications) {
            result.add(applicationTOService.buildPublicApplicationTO(application));
        }
        return result;
    }
}
