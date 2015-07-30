package br.com.funlife.gamification.services.exposed.interfaces;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.exceptions.UnauthorizedException;
import br.com.funlife.gamification.to.RuleTO;
import java.util.List;
import javax.ejb.Remote;
import javax.ws.rs.core.Response;

/**
 * Interface for rules management. The methods inside can be called remotly or
 * with jax-rs rest api.
 *
 * @author Diogenes Firmiano
 */
@Remote
public interface IRulesResource {

  public long createRule(RuleTO ruleTO, long idApp) throws EntityNotFoundException, UnauthorizedException;

  public void updateRule(RuleTO ruleTO, long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

  public void deleteRule(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

  public List<RuleTO> getAllRules(long idApp) throws EntityNotFoundException;

  public RuleTO getRule(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

  public Response restCreateRule(RuleTO ruleTO, long idApp) throws EntityNotFoundException, UnauthorizedException;

  public Response restUpdateRule(RuleTO ruleTO, long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

  public Response restDeleteRule(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;
}
