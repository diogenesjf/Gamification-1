package ch.heigvd.gamification.rest;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.model.Rule;
import ch.heigvd.gamification.services.crud.interfaces.local.IRulesManagerLocal;
import ch.heigvd.gamification.services.to.interfaces.IRulesTOService;
import ch.heigvd.gamification.to.RuleTO;
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
 * REST Service. Expose some services to manage the rules of the application. A
 * rule is defined for a specific action and is used by one or more success to
 * detect when they can be given to the users. The rule is completed when the
 * total points of a user for an action is equals the the goal points of the
 * rule.
 *
 * @author Gaël Jobin
 */
@Path("rules")
public class RulesResource extends GamificationRESTResource {

  @EJB
  IRulesManagerLocal rulesManager;

  @EJB
  IRulesTOService rulesTOService;

  /**
   * Creates a new instance of RulesResource
   */
  public RulesResource() {
  }

  /**
   * Creates a new Rule resource from the provided representation in the current
   * application.
   *
   * @param ruleTO the representation of the new rule
   * @return Response HTTP Code 201 Created
   * @throws EntityNotFoundException application does not exists
   */
  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  public Response createRule(RuleTO ruleTO) throws EntityNotFoundException {
    Rule newRule = new Rule();
    rulesTOService.updateRuleEntity(newRule, ruleTO, getApplication());
    return Response.created(
            context.getAbsolutePathBuilder().path(Long.toString(
                            rulesManager.create(newRule)
                    )).build()
    ).build();
  }

  /**
   * Retrieves a representation of a list of Rule resources.
   *
   * @return List<RuleTO> an list of RuleTO
   * @throws EntityNotFoundException if application does not exists
   */
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public List<RuleTO> getAllRules() throws EntityNotFoundException {
    List<RuleTO> result = new LinkedList<>();
    for (Rule rule : rulesManager.findAll(getApplication())) {
      result.add(rulesTOService.buildPublicRuleTO(rule));
    }
    return result;
  }

  /**
   * Retrieves representation of a Rule resource.
   *
   * @param id unique id of the rule
   * @return an instance of RuleTO
   * @throws EntityNotFoundException rule or application does not exists
   * @throws UnauthorizedException rule does not belong to current application
   */
  @GET
  @Path("{id}")
  @Produces({MediaType.APPLICATION_JSON})
  public RuleTO getRule(@PathParam("id") long id) throws EntityNotFoundException, UnauthorizedException {
    rulesManager.checkRights(id, getApplication());
    return rulesTOService.buildPublicRuleTO(rulesManager.findById(id));
  }

  /**
   * Updates an Rule resource by passing his new representation.
   *
   * @param ruleTO the new representation of the rule
   * @param id id of the rule to update
   * @return Response HTTP Code 204 No Content
   * @throws EntityNotFoundException rule or application does not exists
   * @throws UnauthorizedException rule does not belong to current application
   */
  @PUT
  @Path("{id}")
  @Consumes({MediaType.APPLICATION_JSON})
  public Response updateRule(RuleTO ruleTO, @PathParam("id") long id) throws EntityNotFoundException, UnauthorizedException {
    rulesManager.checkRights(id, getApplication());
    Rule ruleToUpdate = rulesManager.findById(id);
    rulesTOService.updateRuleEntity(ruleToUpdate, ruleTO, getApplication());
    rulesManager.update(ruleToUpdate);
    return Response.noContent().build();
  }

  /**
   * Deletes a Rule resource by passing his unique id.
   *
   * @param id the unique id of the rule to delete
   * @return Response HTTP Code 204 No Content
   * @throws EntityNotFoundException rule or application does not exists
   * @throws UnauthorizedException rule does not belong to current application
   */
  @DELETE
  @Path("{id}")
  public Response deleteRule(@PathParam("id") long id) throws EntityNotFoundException, UnauthorizedException {
    rulesManager.checkRights(id, getApplication());
    rulesManager.delete(id);
    return Response.noContent().build();
  }
}
