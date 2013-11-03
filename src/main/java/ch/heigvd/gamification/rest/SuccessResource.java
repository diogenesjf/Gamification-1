package ch.heigvd.gamification.rest;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.AppUser;
import ch.heigvd.gamification.model.Rule;
import ch.heigvd.gamification.model.Success;
import ch.heigvd.gamification.services.crud.interfaces.IRulesManager;
import ch.heigvd.gamification.services.crud.interfaces.ISuccessManager;
import ch.heigvd.gamification.services.to.interfaces.IAppUsersTOService;
import ch.heigvd.gamification.services.to.interfaces.IRulesTOService;
import ch.heigvd.gamification.services.to.interfaces.ISuccessTOService;
import ch.heigvd.gamification.to.AppUserPublicTO;
import ch.heigvd.gamification.to.GenericOnlyIDTO;
import ch.heigvd.gamification.to.PublicRuleTO;
import ch.heigvd.gamification.to.SuccessTO;
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
@Path("success")
public class SuccessResource {
    
    @Context
    private UriInfo context;
    
    @EJB
    ISuccessManager successManager;
    
    @EJB
    IRulesManager rulesManager;
    
    @EJB
    ISuccessTOService successTOService;
    
    @EJB
    IRulesTOService rulesTOService;
    
    @EJB
    IAppUsersTOService usersTOService;
    
    /**
     * Creates a new instance of SuccessResource
     */
    public SuccessResource() {
    }
        
     /**
     * Creates a new Success resource from the provided representation
     * @return an instance of PublicSuccessTO
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createResource(SuccessTO newSuccessTO) {
        Success newSuccess = new Success();
        successTOService.updateSuccessEntity(newSuccess,newSuccessTO);
        long newSuccessId = successManager.create(newSuccess);
        URI createdURI = context.getAbsolutePathBuilder().path(Long.toString(newSuccessId)).build();
        return Response.created(createdURI).build();
    }
    
    /**
     * Retrieves a representation of a list of Success resources
     * @return an instance of SuccessTO
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<SuccessTO> getResourceList() {
        List<Success> success = successManager.findAll();
        List<SuccessTO> result = new LinkedList<SuccessTO>();
        for(Success singleSuccess : success) {
            result.add(successTOService.buildSuccessTO(singleSuccess));
        }
        return result;
    }
    /**
     * Retrieves representation of an Success resource
     * @param id
     * @return an instance of SuccessTO
     * @throws ch.heigvd.gamification.exceptions.EntityNotFoundException
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public SuccessTO getResource(@PathParam("id") long id) throws EntityNotFoundException {
        Success success = successManager.findById(id);
        SuccessTO successTO = successTOService.buildSuccessTO(success);
        return successTO;
    }
    
    /**
     * Updates an Success resource
     * @return an instance of PublicRuleTO
     */
    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public Response Resource(SuccessTO updatedSuccessTO, @PathParam("id") long id) throws EntityNotFoundException {
        Success successToUpdate = successManager.findById(id);
        successTOService.updateSuccessEntity(successToUpdate, updatedSuccessTO);
        successManager.update(successToUpdate);
        return Response.ok().build();
    }
    
    
    /**
     * Deletes an Success resource
     * @return an instance of SuccessTO
     */
    @DELETE
    @Path("{id}")
    public Response deleteResource(@PathParam("id") long id) throws EntityNotFoundException {
        successManager.delete(id);
        return Response.ok().build();
    }
    
    
    /**
     * Retrieves representation of a liste of Rules linked to a specific Success resource
     * @param id
     * @return a list of PublicRuleTO
     * @throws ch.heigvd.gamification.exceptions.EntityNotFoundException
     */
    @GET
    @Path("{id}/rules")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<PublicRuleTO> getRulesResource(@PathParam("id") long id) throws EntityNotFoundException {
        List<Rule> rules = successManager.findById(id).getRules();
        List<PublicRuleTO> result = new LinkedList<PublicRuleTO>();
        for(Rule rule : rules) {
            result.add(rulesTOService.buildPublicRuleTO(rule));
        }
        return result;
    }

    /**
     * Link one or more Rules to a Success resource
     * @return an instance of PublicRulesTO
     */
    @POST
    @Path("{id}/rules")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response linkRuletoSuccess(List<GenericOnlyIDTO> idsTO, @PathParam("id") long id) throws EntityNotFoundException {
        Success success = successManager.findById(id);
        List<Rule> rules = new LinkedList<Rule>();
        for(GenericOnlyIDTO idTO : idsTO) {
            rules.add(rulesManager.findById(idTO.getId()));
        }
        success.addRules(rules);
        successManager.update(success);
        return Response.ok().build();
    }
    
    /**
     * Retrieves representation of a list of User linked to a specific Success resource
     * @param id
     * @return a list of AppUserPublicTO
     * @throws ch.heigvd.gamification.exceptions.EntityNotFoundException
     */
    @GET
    @Path("{id}/users")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<AppUserPublicTO> getUsersResource(@PathParam("id") long id) throws EntityNotFoundException {
        List<AppUser> users = successManager.findById(id).getUsers();
        List<AppUserPublicTO> result = new LinkedList<AppUserPublicTO>();
        for(AppUser user : users) {
            result.add(usersTOService.buildPublicUserTO(user));
        }
        return result;
    }
    
    /**
     * Delete the link between a Rule and a Success resource
     * @return an instance of PublicRulesTO
     */
    @DELETE
    @Path("{id}/rules/{idRule}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response unlinkRuletoSuccess(@PathParam("id") long id, @PathParam("idRule") long idRule) throws EntityNotFoundException {
        Success success = successManager.findById(id);
        success.getRules().remove(rulesManager.findById(idRule));
        successManager.update(success);
        return Response.ok().build();
    }
}
