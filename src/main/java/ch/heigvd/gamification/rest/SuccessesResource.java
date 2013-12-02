package ch.heigvd.gamification.rest;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.AppUser;
import ch.heigvd.gamification.model.Rule;
import ch.heigvd.gamification.model.Success;
import ch.heigvd.gamification.services.crud.interfaces.local.IAppUsersManagerLocal;
import ch.heigvd.gamification.services.crud.interfaces.local.IRulesManagerLocal;
import ch.heigvd.gamification.services.crud.interfaces.local.ISuccessesManagerLocal;
import ch.heigvd.gamification.services.to.interfaces.IAppUsersTOService;
import ch.heigvd.gamification.services.to.interfaces.IRulesTOService;
import ch.heigvd.gamification.services.to.interfaces.ISuccessesTOService;
import ch.heigvd.gamification.to.AppUserPublicTO;
import ch.heigvd.gamification.to.GenericOnlyIDTO;
import ch.heigvd.gamification.to.RuleTO;
import ch.heigvd.gamification.to.SuccessTO;
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
 * REST Service
 *
 * @author Gaël Jobin
 */
@Path("successes")
public class SuccessesResource extends GamificationRESTResource {

  @EJB
  ISuccessesManagerLocal successManager;

  @EJB
  IAppUsersManagerLocal usersManager;

  @EJB
  IRulesManagerLocal rulesManager;

  @EJB
  ISuccessesTOService successTOService;

  @EJB
  IRulesTOService rulesTOService;

  @EJB
  IAppUsersTOService usersTOService;

  /**
   * Creates a new instance of SuccessResource
   */
  public SuccessesResource() {
  }

  /**
   * Creates a new Success resource from the provided representation
   *
   * @param successTO the new success representation
   * @return Response HTTP Code 201 Created
   */
  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  public Response createResource(SuccessTO successTO) throws EntityNotFoundException {
    Success success = new Success();
    successTOService.updateSuccessEntity(success, successTO, getApplication());
    return Response.created(
            context.getAbsolutePathBuilder().path(Long.toString(
                            successManager.create(success)
                    )).build()
    ).build();
  }

  /**
   * Retrieves a representation of a list of Success resources
   *
   * @return an instance of SuccessTO
   */
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public List<SuccessTO> getResources() {
    List<Success> success = successManager.findAll();
    List<SuccessTO> result = new LinkedList<>();
    for (Success singleSuccess : success) {
      result.add(successTOService.buildSuccessTO(singleSuccess));
    }
    return result;
  }

  /**
   * Retrieves representation of an Success resource
   *
   * @param id
   * @return an instance of SuccessTO
   * @throws ch.heigvd.gamification.exceptions.EntityNotFoundException
   */
  @GET
  @Path("{id}")
  @Produces({MediaType.APPLICATION_JSON})
  public SuccessTO getResource(@PathParam("id") long id) throws EntityNotFoundException {
    Success success = successManager.findById(id);
    SuccessTO successTO = successTOService.buildSuccessTO(success);
    return successTO;
  }

  /**
   * Updates an Success resource
   *
   * @param updatedSuccessTO
   * @param id
   * @return instance of PublicRuleTO
   * @throws EntityNotFoundException success or application not found
   */
  @PUT
  @Path("{id}")
  @Consumes({MediaType.APPLICATION_JSON})
  public Response updateResource(SuccessTO updatedSuccessTO, @PathParam("id") long id) throws EntityNotFoundException {
    Success successToUpdate = successManager.findById(id);
    successTOService.updateSuccessEntity(successToUpdate, updatedSuccessTO, getApplication());
    successManager.update(successToUpdate);
    return Response.noContent().build();
  }

  /**
   * Deletes an Success resource
   *
   * @param id
   * @return Response HTTP Code 204 No Content
   * @throws EntityNotFoundException success not found
   */
  @DELETE
  @Path("{id}")
  public Response deleteResource(@PathParam("id") long id) throws EntityNotFoundException {
    //TODO control rights from application
    successManager.delete(id);
    return Response.noContent().build();
  }

  /**
   * Retrieves representation of a liste of Rules linked to a specific Success
   * resource
   *
   * @param id
   * @return List<RuleTO> a list of PublicRuleTO
   * @throws EntityNotFoundException success does not exists
   */
  @GET
  @Path("{id}/rules")
  @Produces({MediaType.APPLICATION_JSON})
  public List<RuleTO> getSuccessRulesResource(@PathParam("id") long id) throws EntityNotFoundException {
    List<RuleTO> result = new LinkedList<>();
    for (Rule rule : successManager.findById(id).getRules()) {
      result.add(rulesTOService.buildPublicRuleTO(rule));
    }
    return result;
  }

  /**
   * Retrieves representation of a list of User linked to a specific Success
   * resource
   *
   * @param id
   * @return a list of AppUserPublicTO
   * @throws ch.heigvd.gamification.exceptions.EntityNotFoundException
   */
  @GET
  @Path("{id}/users")
  @Produces({MediaType.APPLICATION_JSON})
  public List<AppUserPublicTO> getSuccessUsersResource(@PathParam("id") long id) throws EntityNotFoundException {
    List<AppUser> users = usersManager.findAllBySuccess(id, getApplication());
    List<AppUserPublicTO> result = new LinkedList<>();
    for (AppUser user : users) {
      result.add(usersTOService.buildPublicUserTO(user));
    }
    return result;
  }

  /**
   * Link one or more Rules to a Success resource
   *
   * @param idTO
   * @param id
   * @return an instance of PublicRulesTO
   * @throws ch.heigvd.gamification.exceptions.EntityNotFoundException
   */
  @POST
  @Path("{id}/rules")
  @Consumes({MediaType.APPLICATION_JSON})
  public Response linkRuletoSuccess(GenericOnlyIDTO idTO, @PathParam("id") long id) throws EntityNotFoundException {
    Success success = successManager.findById(id);
    Rule rule = rulesManager.findById(idTO.getId());
    success.addRule(rule);
    return Response.created(null).build();
  }

  /**
   * Delete the link between a Rule and a Success resource
   *
   * @param id
   * @param idRule
   * @return an instance of PublicRulesTO
   * @throws EntityNotFoundException
   */
  @DELETE
  @Path("{id}/rules/{idRule}")
  @Consumes({MediaType.APPLICATION_JSON})
  public Response unlinkRuleFromSuccess(@PathParam("id") long id, @PathParam("idRule") long idRule) throws EntityNotFoundException {
    Success success = successManager.findById(id);
    success.getRules().remove(rulesManager.findById(idRule));
    successManager.update(success);
    return Response.noContent().build();
  }
}
