package br.com.funlife.gamification.services.exposed;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.exceptions.UnauthorizedException;
import br.com.funlife.gamification.model.AppUser;
import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.model.Rule;
import br.com.funlife.gamification.model.Success;
import br.com.funlife.gamification.services.crud.interfaces.IAppUsersManager;
import br.com.funlife.gamification.services.crud.interfaces.IApplicationsManager;
import br.com.funlife.gamification.services.crud.interfaces.IRulesManager;
import br.com.funlife.gamification.services.crud.interfaces.ISuccessesManager;
import br.com.funlife.gamification.services.exposed.interfaces.ISuccessesResource;
import br.com.funlife.gamification.services.to.interfaces.IAppUsersTOService;
import br.com.funlife.gamification.services.to.interfaces.IRulesTOService;
import br.com.funlife.gamification.services.to.interfaces.ISuccessesTOService;
import br.com.funlife.gamification.to.AppUserPublicTO;
import br.com.funlife.gamification.to.GenericOnlyIDTO;
import br.com.funlife.gamification.to.RuleTO;
import br.com.funlife.gamification.to.SuccessTO;
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
 * REST and Remote Service. Expose some service for successes management. A
 * success is defined by a name and a badge (an image).
 *
 * @author Diogenes Firmiano
 */
@Stateless
@Path("successes")
public class SuccessesResource implements ISuccessesResource {

  @Context
  private UriInfo context;

  @EJB
  private ISuccessesManager successManager;

  @EJB
  private IAppUsersManager usersManager;

  @EJB
  private IRulesManager rulesManager;

  @EJB
  private ISuccessesTOService successTOService;

  @EJB
  private IRulesTOService rulesTOService;

  @EJB
  private IAppUsersTOService usersTOService;

  @EJB
  private IApplicationsManager appManager;

  /**
   * Creates a new instance of SuccessResource
   */
  public SuccessesResource() {
  }

  /**
   * Creates a new Success resource from the provided representation.
   *
   * @param successTO the representation of the new success
   * @param idApp id of the application
   * @return Response HTTP Code 201 Created
   * @throws EntityNotFoundException application does not exists
   */
  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  @Override
  public Response restCreateSuccess(SuccessTO successTO, @HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException {
    return Response.created(
            context.getAbsolutePathBuilder().path(Long.toString(
                            createSuccess(successTO, idApp)
                    )).build()
    ).build();
  }

  @Override
  public long createSuccess(SuccessTO successTO, long idApp) throws EntityNotFoundException {
    Success success = new Success();
    successTOService.updateSuccessEntity(success, successTO, appManager.findById(idApp));
    return successManager.create(success);
  }

  /**
   * Retrieves a representation of a list of Success resources.
   *
   * @param idApp id of the application
   * @return List<SuccessTO> an list of SuccessTO
   * @throws EntityNotFoundException application does not exists
   */
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  @Override
  public List<SuccessTO> getSuccesses(@HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException {
    List<SuccessTO> result = new LinkedList<>();
    for (Success singleSuccess : successManager.findAll(appManager.findById(idApp))) {
      result.add(successTOService.buildSuccessTO(singleSuccess));
    }
    return result;
  }

  /**
   * Retrieves representation of a Success resource.
   *
   * @param id the unique id of the success
   * @param idApp id of the application
   * @return an instance of SuccessTO
   * @throws EntityNotFoundException success or application does not exists
   * @throws UnauthorizedException success does not belong current application
   */
  @GET
  @Path("{id}")
  @Produces({MediaType.APPLICATION_JSON})
  @Override
  public SuccessTO getSuccess(@PathParam("id") long id, @HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException, UnauthorizedException {
    return successTOService.buildSuccessTO(successManager.findById(id, appManager.findById(idApp)));
  }

  /**
   * Update a Success resource by passing his new representation.
   *
   * @param successTO new representation
   * @param id the unique id of the success
   * @param idApp id of the application
   * @return Response HTTP Code 204 No Content
   * @throws EntityNotFoundException success or application not found
   * @throws UnauthorizedException success does not belong application
   */
  @PUT
  @Path("{id}")
  @Consumes({MediaType.APPLICATION_JSON})
  @Override
  public Response restUpdateSuccess(SuccessTO successTO, @PathParam("id") long id, @HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException, UnauthorizedException {
    updateSuccess(successTO, id, idApp);
    return Response.noContent().build();
  }

  @Override
  public void updateSuccess(SuccessTO successTO, long id, long idApp) throws EntityNotFoundException, UnauthorizedException {
    Application app = appManager.findById(idApp);
    Success successToUpdate = successManager.findById(id, app);
    successTOService.updateSuccessEntity(successToUpdate, successTO, app);
    successManager.update(successToUpdate, app);
  }

  /**
   * Delete an Success resource by passing his id.
   *
   * @param id the unique id of the success to delete
   * @param idApp id of the application
   * @return Response HTTP Code 204 No Content
   * @throws EntityNotFoundException success not found
   * @throws UnauthorizedException success does not belong application
   */
  @DELETE
  @Path("{id}")
  @Override
  public Response restDeleteSuccess(@PathParam("id") long id, @HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException, UnauthorizedException {
    deleteSuccess(id, idApp);
    return Response.noContent().build();
  }

  @Override
  public void deleteSuccess(long id, long idApp) throws EntityNotFoundException, UnauthorizedException {
    successManager.delete(id, appManager.findById(idApp));
  }

  /**
   * Retrieves representation of a liste of Rules linked to a specific Success
   * resource.
   *
   * @param id the unique id of the success
   * @param idApp id of the application
   * @return List<RuleTO> a list of PublicRuleTO
   * @throws EntityNotFoundException success does not exists
   * @throws UnauthorizedException success does not belong application
   */
  @GET
  @Path("{id}/rules")
  @Produces({MediaType.APPLICATION_JSON})
  @Override
  public List<RuleTO> getSuccessRules(@PathParam("id") long id, @HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException, UnauthorizedException {
    List<RuleTO> result = new LinkedList<>();
    for (Rule rule : successManager.findById(id, appManager.findById(idApp)).getRules()) {
      result.add(rulesTOService.buildPublicRuleTO(rule));
    }
    return result;
  }

  /**
   * Retrieves representation of a list of User linked to a specific Success
   * resource
   *
   * @param id unique id of the success
   * @param idApp id of the application
   * @return List<AppUserPublicTO> a list of AppUserPublicTO
   * @throws EntityNotFoundException success does not exists
   * @throws UnauthorizedException success does not belong application
   */
  @GET
  @Path("{id}/users")
  @Produces({MediaType.APPLICATION_JSON})
  @Override
  public List<AppUserPublicTO> getSuccessUsers(@PathParam("id") long id, @HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException, UnauthorizedException {
    Application app = appManager.findById(idApp);
    Success success = successManager.findById(id, app);
    List<AppUserPublicTO> result = new LinkedList<>();
    for (AppUser user : usersManager.findAllBySuccess(success, app)) {
      result.add(usersTOService.buildPublicUserTO(user));
    }
    return result;
  }

  /**
   * Link one Rules to a Success resource.
   *
   * @param to the transfer object with rule id
   * @param id the unique id of the success
   * @param idApp id of the application
   * @return Response HTTP Code 201 Created
   * @throws EntityNotFoundException rule or success does not exists
   * @throws UnauthorizedException success or rule does not belong application
   */
  @POST
  @Path("{id}/rules")
  @Consumes({MediaType.APPLICATION_JSON})
  @Override
  public Response restLinkRuletoSuccess(GenericOnlyIDTO to, @PathParam("id") long id, @HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException, UnauthorizedException {
    linkRuletoSuccess(to, id, idApp);
    return Response.created(null).build();
  }

  @Override
  public void linkRuletoSuccess(GenericOnlyIDTO to, long id, long idApp) throws EntityNotFoundException, UnauthorizedException {
    Application app = appManager.findById(idApp);
    Success success = successManager.findById(id, app);
    success.addRule(rulesManager.findById(to.getId(), app));
    successManager.update(success, app);
  }

  /**
   * Delete the link between a Rule and a Success resource.
   *
   * @param id unique id of the success
   * @param idRule unique id of the rule
   * @param idApp id of the application
   * @return Response HTTP Code 204 No Content
   * @throws EntityNotFoundException rule or success does not exists
   * @throws UnauthorizedException rule or success does not belong current
   * application
   */
  @DELETE
  @Path("{id}/rules/{idrule}")
  @Consumes({MediaType.APPLICATION_JSON})
  @Override
  public Response restUnlinkRuleFromSuccess(@PathParam("id") long id, @PathParam("idrule") long idRule, @HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException, UnauthorizedException {
    unlinkRuleFromSuccess(id, idRule, idApp);
    return Response.noContent().build();
  }

  @Override
  public void unlinkRuleFromSuccess(long id, long idRule, long idApp) throws EntityNotFoundException, UnauthorizedException {
    Application app = appManager.findById(idApp);
    Success success = successManager.findById(id, app);
    success.getRules().remove(rulesManager.findById(idRule, app));
    successManager.update(success, app);
  }
}
