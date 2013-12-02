package ch.heigvd.gamification.rest;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.ActionType;
import ch.heigvd.gamification.services.to.interfaces.IActionTypesTOService;
import ch.heigvd.gamification.to.PublicActionTypeTO;
import ch.heigvd.gamification.services.crud.interfaces.local.IActionTypesManagerLocal;
import java.net.URI;
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
 * @author Gaël Jobin
 */
@Stateless
@Path("actiontypes")
public class ActionTypesResource extends GamificationRESTResource {
    
    @EJB
    IActionTypesManagerLocal actionTypesManager;
    
    @EJB
    IActionTypesTOService actionTypesTOService;
    
    /**
     * Creates a new instance of ActionTypesResource
     */
    public ActionTypesResource() {
    }
    
    /**
     * Creates a new ActionType resource from the provided representation
     * @param newActionTypeTO
     * @return an instance of PublicActionTypeTO
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createResource(PublicActionTypeTO newActionTypeTO) {
        ActionType newActionType = new ActionType();
        actionTypesTOService.updateActionTypeEntity(newActionType,newActionTypeTO);
        long newActionTypeId = actionTypesManager.create(newActionType);
        URI createdURI = context.getAbsolutePathBuilder().path(Long.toString(newActionTypeId)).build();
        return Response.created(createdURI).build();
    }
    
    /**
     * Retrieves a representation of a list of ActionType resources
     * @return an instance of PublicActionTypeTO
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<PublicActionTypeTO> getResources() {
        List<ActionType> actionTypes = actionTypesManager.findAll();
        List<PublicActionTypeTO> result = new LinkedList<>();
        for(ActionType actionType : actionTypes) {
            result.add(actionTypesTOService.buildPublicActionTypeTO(actionType));
        }
        return result;
    }
    
    /**
     * Retrieves representation of an ActionType resource
     * @param id
     * @return an instance of PublicActionTypeTO
     * @throws ch.heigvd.gamification.exceptions.EntityNotFoundException
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public PublicActionTypeTO getResource(@PathParam("id") long id) throws EntityNotFoundException {
        ActionType actionType = actionTypesManager.findById(id);
        PublicActionTypeTO actionTypeTO = actionTypesTOService.buildPublicActionTypeTO(actionType);
        return actionTypeTO;
    }
    
    /**
     * Updates an ActionType resource
     * @param updatedActionTypeTO
     * @param id
     * @return an instance of PublicActionTypeTO
     * @throws ch.heigvd.gamification.exceptions.EntityNotFoundException
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateResource(PublicActionTypeTO updatedActionTypeTO, @PathParam("id") long id) throws EntityNotFoundException {
        ActionType actionTypeToUpdate = actionTypesManager.findById(id);
        actionTypesTOService.updateActionTypeEntity(actionTypeToUpdate, updatedActionTypeTO);
        actionTypesManager.update(actionTypeToUpdate);
        return Response.ok().build();
    }
    
    
    /**
     * Deletes an ActionType resource
     * @param id
     * @return an instance of PublicActionTypeTO
     * @throws ch.heigvd.gamification.exceptions.EntityNotFoundException
     */
    @DELETE
    @Path("{id}")
    public Response deleteResource(@PathParam("id") long id) throws EntityNotFoundException {
        actionTypesManager.delete(id);
        return Response.ok().build();
    } 
}
