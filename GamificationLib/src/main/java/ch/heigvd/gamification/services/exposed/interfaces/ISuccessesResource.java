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
 *
 * @author Alexandre Perusset
 */
@Remote
public interface ISuccessesResource {
  
  public Response createSuccess(SuccessTO successTO, long idApp) throws EntityNotFoundException;
  
  public List<SuccessTO> getSuccesses(long idApp) throws EntityNotFoundException;
  
  public SuccessTO getSuccess(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;
  
  public Response updateSuccess(SuccessTO successTO, long id, long idApp) throws EntityNotFoundException, UnauthorizedException;
  
  public Response deleteSuccess(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;
  
  public List<RuleTO> getSuccessRules(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;
  
  public List<AppUserPublicTO> getSuccessUsers(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;
  
  public Response linkRuletoSuccess(GenericOnlyIDTO to, long id, long idApp) throws EntityNotFoundException, UnauthorizedException;
  
  public Response unlinkRuleFromSuccess(long id, long idRule, long idApp) throws EntityNotFoundException, UnauthorizedException;
}
