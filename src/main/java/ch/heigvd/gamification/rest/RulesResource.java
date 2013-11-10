package ch.heigvd.gamification.rest;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Rule;
import ch.heigvd.gamification.services.crud.interfaces.IRulesManager;
import ch.heigvd.gamification.services.to.interfaces.IRulesTOService;
import ch.heigvd.gamification.to.PublicRuleTO;
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
 * REST SERVICE
 *
 * @author Gaël Jobin
 */
@Stateless
@Path("rules")
public class RulesResource {
    
    @Context
    private UriInfo context;
    
    @EJB
    IRulesManager rulesManager;
    
    @EJB
    IRulesTOService rulesTOService;
    
    /**
     * Creates a new instance of RulesResource
     */
    public RulesResource() {
    }
    
    /**
     * Creates a new Rule resource from the provided representation
     * @return an instance of PublicRuleTO
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createResource(PublicRuleTO newRuleTO) {
        Rule newRule = new Rule();
        rulesTOService.updateRuleEntity(newRule,newRuleTO);
        long newRuleId = rulesManager.create(newRule);
        URI createdURI = context.getAbsolutePathBuilder().path(Long.toString(newRuleId)).build();
        return Response.created(createdURI).build();
    }
    
    /**
     * Retrieves a representation of a list of Rule resources
     * @return an instance of PublicRuleTO
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<PublicRuleTO> getResources() {
        List<Rule> rules = rulesManager.findAll();
        List<PublicRuleTO> result = new LinkedList<PublicRuleTO>();
        for(Rule rule : rules) {
            result.add(rulesTOService.buildPublicRuleTO(rule));
        }
        return result;
    }
    
    /**
     * Retrieves representation of an Rule resource
     * @param id
     * @return an instance of PublicRuleTO
     * @throws ch.heigvd.gamification.exceptions.EntityNotFoundException
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public PublicRuleTO getResource(@PathParam("id") long id) throws EntityNotFoundException {
        Rule rule = rulesManager.findById(id);
        PublicRuleTO ruleTO = rulesTOService.buildPublicRuleTO(rule);
        return ruleTO;
    }
    
    /**
     * Updates an Rule resource
     * @return an instance of PublicRuleTO
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateResource(PublicRuleTO updatedRuleTO, @PathParam("id") long id) throws EntityNotFoundException {
        Rule ruleToUpdate = rulesManager.findById(id);
        rulesTOService.updateRuleEntity(ruleToUpdate, updatedRuleTO);
        rulesManager.update(ruleToUpdate);
        return Response.ok().build();
    }
    
    
    /**
     * Deletes an Rule resource
     * @return an instance of PublicRuleTO
     */
    @DELETE
    @Path("{id}")
    public Response deleteResource(@PathParam("id") long id) throws EntityNotFoundException {
        rulesManager.delete(id);
        return Response.ok().build();
    }
}