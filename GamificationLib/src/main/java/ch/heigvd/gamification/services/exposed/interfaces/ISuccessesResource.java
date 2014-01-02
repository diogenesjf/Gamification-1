package ch.heigvd.gamification.services.exposed.interfaces;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.to.AppUserPublicTO;
import ch.heigvd.gamification.to.GenericOnlyIDTO;
import ch.heigvd.gamification.to.RuleTO;
import ch.heigvd.gamification.to.SuccessTO;
import java.util.List;
import javax.ejb.Remote;
import javax.ws.rs.core.Response;

/**
 * Interface for successes management. The methods inside can be called remotly
 * or with jax-rs rest api.
 *
 * @author Alexandre Perusset
 */
@Remote
public interface ISuccessesResource {

  public long createSuccess(SuccessTO successTO, long idApp) throws EntityNotFoundException;

  public void updateSuccess(SuccessTO successTO, long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

  public void deleteSuccess(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

  public void linkRuletoSuccess(GenericOnlyIDTO to, long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

  public void unlinkRuleFromSuccess(long id, long idRule, long idApp) throws EntityNotFoundException, UnauthorizedException;

  public List<SuccessTO> getSuccesses(long idApp) throws EntityNotFoundException;

  public SuccessTO getSuccess(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

  public List<RuleTO> getSuccessRules(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

  public List<AppUserPublicTO> getSuccessUsers(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

  public Response restCreateSuccess(SuccessTO successTO, long idApp) throws EntityNotFoundException;

  public Response restUpdateSuccess(SuccessTO successTO, long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

  public Response restDeleteSuccess(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

  public Response restLinkRuletoSuccess(GenericOnlyIDTO to, long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

  public Response restUnlinkRuleFromSuccess(long id, long idRule, long idApp) throws EntityNotFoundException, UnauthorizedException;
}
