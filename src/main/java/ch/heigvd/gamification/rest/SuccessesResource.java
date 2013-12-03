package ch.heigvd.gamification.rest;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
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
 * REST Service. Expose some service for successes management. A success is
 * defined by a name and a badge (an image).
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
   * Creates a new Success resource from the provided representation.
   *
   * @param successTO the representation of the new success
   * @return Response HTTP Code 201 Created
   * @throws EntityNotFoundException application does not exists
   */
  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  public Response createSuccess(SuccessTO successTO) throws EntityNotFoundException {
    Success success = new Success();
    successTOService.updateSuccessEntity(success, successTO, getApplication());
    return Response.created(
            context.getAbsolutePathBuilder().path(Long.toString(
                            successManager.create(success)
                    )).build()
    ).build();
  }

  /**
   * Retrieves a representation of a list of Success resources.
   *
   * @return List<SuccessTO> an list of SuccessTO
   * @throws EntityNotFoundException application does not exists
   */
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public List<SuccessTO> getSuccesses() throws EntityNotFoundException {
    List<SuccessTO> result = new LinkedList<>();
    for (Success singleSuccess : successManager.findAll(getApplication())) {
      result.add(successTOService.buildSuccessTO(singleSuccess));
    }
    return result;
  }

  /**
   * Retrieves representation of a Success resource.
   *
   * @param id the unique id of the success
   * @return an instance of SuccessTO
   * @throws EntityNotFoundException success or application does not exists
   * @throws UnauthorizedException success does not belong current application
   */
  @GET
  @Path("{id}")
  @Produces({MediaType.APPLICATION_JSON})
  public SuccessTO getSuccess(@PathParam("id") long id) throws EntityNotFoundException, UnauthorizedException {
    successManager.checkRights(id, getApplication());
    return successTOService.buildSuccessTO(successManager.findById(id));
  }

  /**
   * Update a Success resource by passing his new representation.
   *
   * @param successTO new representation
   * @param id the unique id of the success
   * @return Response HTTP Code 204 No Content
   * @throws EntityNotFoundException success or application not found
   * @throws UnauthorizedException success does not belong current application
   */
  @PUT
  @Path("{id}")
  @Consumes({MediaType.APPLICATION_JSON})
  public Response updateSuccess(SuccessTO successTO, @PathParam("id") long id) throws EntityNotFoundException, UnauthorizedException {
    successManager.checkRights(id, getApplication());
    Success successToUpdate = successManager.findById(id);
    successTOService.updateSuccessEntity(successToUpdate, successTO, getApplication());
    successManager.update(successToUpdate);
    return Response.noContent().build();
  }

  /**
   * Delete an Success resource by passing his id.
   *
   * @param id the unique id of the success to delete
   * @return Response HTTP Code 204 No Content
   * @throws EntityNotFoundException success not found
   * @throws UnauthorizedException success does not belong current application
   */
  @DELETE
  @Path("{id}")
  public Response deleteSuccess(@PathParam("id") long id) throws EntityNotFoundException, UnauthorizedException {
    successManager.checkRights(id, getApplication());
    successManager.delete(id);
    return Response.noContent().build();
  }

  /**
   * Retrieves representation of a liste of Rules linked to a specific Success
   * resource.
   *
   * @param id the unique id of the success
   * @return List<RuleTO> a list of PublicRuleTO
   * @throws EntityNotFoundException success does not exists
   * @throws UnauthorizedException success does not belong current application
   */
  @GET
  @Path("{id}/rules")
  @Produces({MediaType.APPLICATION_JSON})
  public List<RuleTO> getSuccessRules(@PathParam("id") long id) throws EntityNotFoundException, UnauthorizedException {
    successManager.checkRights(id, getApplication());
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
   * @param id unique id of the success
   * @return List<AppUserPublicTO> a list of AppUserPublicTO
   * @throws EntityNotFoundException success does not exists
   * @throws UnauthorizedException success does not belong current application
   */
  @GET
  @Path("{id}/users")
  @Produces({MediaType.APPLICATION_JSON})
  public List<AppUserPublicTO> getSuccessUsers(@PathParam("id") long id) throws EntityNotFoundException, UnauthorizedException {
    successManager.checkRights(id, getApplication());
    List<AppUserPublicTO> result = new LinkedList<>();
    for (AppUser user : usersManager.findAllBySuccess(id)) {
      result.add(usersTOService.buildPublicUserTO(user));
    }
    return result;
  }

  /**
   * Link one Rules to a Success resource.
   *
   * @param to the transfer object with rule id
   * @param id the unique id of the success
   * @return Response HTTP Code 201 Created
   * @throws EntityNotFoundException rule or success does not exists
   * @throws UnauthorizedException success or rule does not belong current
   * application
   */
  @POST
  @Path("{id}/rules")
  @Consumes({MediaType.APPLICATION_JSON})
  public Response linkRuletoSuccess(GenericOnlyIDTO to, @PathParam("id") long id) throws EntityNotFoundException, UnauthorizedException {
    successManager.checkRights(id, getApplication());
    rulesManager.checkRights(to.getId(), getApplication());
    successManager.findById(id).addRule(rulesManager.findById(to.getId()));
    return Response.created(null).build();
  }

  /**
   * Delete the link between a Rule and a Success resource.
   *
   * @param id unique id of the success
   * @param idRule unique id of the rule
   * @return Response HTTP Code 204 No Content
   * @throws EntityNotFoundException rule or success does not exists
   * @throws UnauthorizedException rule or success does not belong current
   * application
   */
  @DELETE
  @Path("{id}/rules/{idrule}")
  @Consumes({MediaType.APPLICATION_JSON})
  public Response unlinkRuleFromSuccess(@PathParam("id") long id, @PathParam("idrule") long idRule) throws EntityNotFoundException, UnauthorizedException {
    successManager.checkRights(id, getApplication());
    rulesManager.checkRights(idRule, getApplication());
    Success success = successManager.findById(id);
    success.getRules().remove(rulesManager.findById(idRule));
    successManager.update(success);
    return Response.noContent().build();
  }
}
