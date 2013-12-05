package ch.heigvd.gamification.services.to.interfaces;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Rule;
import ch.heigvd.gamification.to.RuleTO;
import javax.ejb.Local;

/**
 *
 * @author Gaël Jobin
 */
@Local
public interface IRulesTOService {

  public RuleTO buildPublicRuleTO(Rule source);

  public void updateRuleEntity(Rule existing, RuleTO state, Application application) throws EntityNotFoundException, UnauthorizedException;
}
