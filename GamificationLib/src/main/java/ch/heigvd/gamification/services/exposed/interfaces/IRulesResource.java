package ch.heigvd.gamification.services.exposed.interfaces;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.to.RuleTO;
import java.util.List;
import javax.ejb.Remote;
import javax.ws.rs.core.Response;

/**
 *
 * @author Alexandre Perusset
 */
@Remote
public interface IRulesResource {
  
  public Response createRule(RuleTO ruleTO, long idApp) throws EntityNotFoundException, UnauthorizedException;
  
  public List<RuleTO> getAllRules(long idApp) throws EntityNotFoundException;
  
  public RuleTO getRule(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;
  
  public Response updateRule(RuleTO ruleTO, long id, long idApp) throws EntityNotFoundException, UnauthorizedException;
  
  public Response deleteRule(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;
}
