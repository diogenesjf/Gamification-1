/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package ch.heigvd.gamification.rest;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Rule;
import ch.heigvd.gamification.model.Success;
import ch.heigvd.gamification.services.crud.RulesManagerLocal;
import ch.heigvd.gamification.services.to.RulesTOServiceLocal;
import ch.heigvd.gamification.services.to.interfaces.ISuccessTOService;
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
    RulesManagerLocal rulesManager;
    
    @EJB
    RulesTOServiceLocal rulesTOService;
    
    @EJB
    ISuccessTOService successTOService;
    
    /**
     * Creates a new instance of EmployeesResource
     */
    public RulesResource() {
    }
    
    /**
     * Creates a new Employee resource from the provided representation
     * @return an instance of PublicEmployeeTO
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createResource(PublicRuleTO newRuleTO) {
        Rule newRule = new Rule();
        rulesTOService.updateRuleEntity(newRule,newRuleTO);
        long newRuleId = rulesManager.create(newRule);
        URI createdURI = context.getAbsolutePathBuilder().path(Long.toString(newRuleId)).build();
        return Response.created(createdURI).build();
    }
    
    /**
     * Retrieves a representation of a list of Employee resources
     * @return an instance of PublicEmployeeTO
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<PublicRuleTO> getResourceList() {
        List<Rule> rules = rulesManager.findAll();
        List<PublicRuleTO> result = new LinkedList<PublicRuleTO>();
        for(Rule rule : rules) {
            result.add(rulesTOService.buildPublicRuleTO(rule));
        }
        return result;
    }
    
    /**
     * Retrieves representation of an Employee resource
     * @param id
     * @return an instance of PublicEmployeeTO
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
     * Updates an Employee resource
     * @return an instance of PublicEmployeeTO
     */
    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public Response Resource(PublicRuleTO updatedRuleTO, @PathParam("id") long id) throws EntityNotFoundException {
        Rule ruleToUpdate = rulesManager.findById(id);
        rulesTOService.updateRuleEntity(ruleToUpdate, updatedRuleTO);
        rulesManager.update(ruleToUpdate);
        return Response.ok().build();
    }
    
    
    /**
     * Deletes an Employee resource
     * @return an instance of PublicEmployeeTO
     */
    @DELETE
    @Path("{id}")
    public Response deleteResource(@PathParam("id") long id) throws EntityNotFoundException {
        rulesManager.delete(id);
        return Response.ok().build();
    }
    
    @GET
    @Path("{id}/success")
    public List<SuccessTO> getUserSuccess(@PathParam("id") long id) throws EntityNotFoundException {
        //If we want another order, use a parametrized NamedQuery
        List<SuccessTO> successTO = new LinkedList<>();
        for (Success success : rulesManager.findById(id).getSuccess()) {
            successTO.add(successTOService.buildSuccessTO(success));
        }
        return successTO;
    }
}