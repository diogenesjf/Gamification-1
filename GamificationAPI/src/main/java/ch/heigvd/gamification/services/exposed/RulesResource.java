package ch.heigvd.gamification.services.exposed;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Rule;
import ch.heigvd.gamification.services.crud.interfaces.IApplicationsManager;
import ch.heigvd.gamification.services.crud.interfaces.IRulesManager;
import ch.heigvd.gamification.services.exposed.interfaces.IRulesResource;
import ch.heigvd.gamification.services.to.interfaces.IRulesTOService;
import ch.heigvd.gamification.to.RuleTO;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
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
 * REST Service. Expose some services to manage the rules of the application. A
 * rule is defined for a specific action and is used by one or more success to
 * detect when they can be given to the users. The rule is completed when the
 * total points of a user for an action is equals the the goal points of the
 * rule.
 *
 * @author Gaël Jobin
 */
@Stateless
@Path("rules")
public class RulesResource implements IRulesResource {

    @Context
    private UriInfo context;

    @EJB
    private IRulesManager rulesManager;

    @EJB
    private IRulesTOService rulesTOService;

    @EJB
    private IApplicationsManager appManager;

    /**
     * Creates a new instance of RulesResource
     */
    public RulesResource() {
    }

    /**
     * Creates a new Rule resource from the provided representation in the
     * current application.
     *
     * @param ruleTO the representation of the new rule
     * @param idApp id of the application
     * @return Response HTTP Code 201 Created
     * @throws EntityNotFoundException application does not exists
     * @throws UnauthorizedException rule action does not belong to application
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Override
    public Response restCreateRule(RuleTO ruleTO, @HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException, UnauthorizedException {
        return Response.created(
                context.getAbsolutePathBuilder().path(Long.toString(
                                createRule(ruleTO, idApp)
                        )).build()
        ).build();
    }

    @Override
    public long createRule(RuleTO ruleTO, long idApp) throws EntityNotFoundException, UnauthorizedException {
        Rule newRule = new Rule();
        rulesTOService.updateRuleEntity(newRule, ruleTO, appManager.findById(idApp));
        return rulesManager.create(newRule);
    }
    
    /**
     * Retrieves a representation of a list of Rule resources.
     *
     * @param idApp id of the application
     * @return List<RuleTO> an list of RuleTO
     * @throws EntityNotFoundException if application does not exists
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public List<RuleTO> getAllRules(@HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException {
        List<RuleTO> result = new LinkedList<>();
        for (Rule rule : rulesManager.findAll(appManager.findById(idApp))) {
            result.add(rulesTOService.buildPublicRuleTO(rule));
        }
        return result;
    }

    /**
     * Retrieves representation of a Rule resource.
     *
     * @param id unique id of the rule
     * @param idApp id of the application
     * @return an instance of RuleTO
     * @throws EntityNotFoundException rule or application does not exists
     * @throws UnauthorizedException rule does not belong to current application
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public RuleTO getRule(@PathParam("id") long id, @HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException, UnauthorizedException {
        return rulesTOService.buildPublicRuleTO(rulesManager.findById(id, appManager.findById(idApp)));
    }

    /**
     * Updates an Rule resource by passing his new representation.
     *
     * @param ruleTO the new representation of the rule
     * @param id id of the rule to update
     * @param idApp id of the application
     * @return Response HTTP Code 204 No Content
     * @throws EntityNotFoundException rule or application does not exists
     * @throws UnauthorizedException rule does not belong to current application
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Override
    public Response restUpdateRule(RuleTO ruleTO, @PathParam("id") long id, @HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException, UnauthorizedException {
        updateRule(ruleTO, id, idApp);
        return Response.noContent().build();
    }

    @Override
    public void updateRule(RuleTO ruleTO, long id, long idApp) throws EntityNotFoundException, UnauthorizedException {
        Application app = appManager.findById(idApp);
        Rule ruleToUpdate = rulesManager.findById(id, app);
        rulesTOService.updateRuleEntity(ruleToUpdate, ruleTO, app);
        rulesManager.update(ruleToUpdate, app);
    }
    
    /**
     * Deletes a Rule resource by passing his unique id.
     *
     * @param id the unique id of the rule to delete
     * @param idApp id of the application
     * @return Response HTTP Code 204 No Content
     * @throws EntityNotFoundException rule or application does not exists
     * @throws UnauthorizedException rule does not belong to current application
     */
    @DELETE
    @Path("{id}")
    @Override
    public Response restDeleteRule(@PathParam("id") long id, @HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException, UnauthorizedException {
        deleteRule(id, idApp);
        return Response.noContent().build();
    }

    @Override
    public void deleteRule(long id, long idApp) throws EntityNotFoundException, UnauthorizedException {
        rulesManager.delete(id, appManager.findById(idApp));
    }
}
