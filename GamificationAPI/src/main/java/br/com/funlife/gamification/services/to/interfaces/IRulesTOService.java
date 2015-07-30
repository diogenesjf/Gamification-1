package br.com.funlife.gamification.services.to.interfaces;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.exceptions.UnauthorizedException;
import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.model.Rule;
import br.com.funlife.gamification.to.RuleTO;
import javax.ejb.Local;

/**
 * This interface provides methods for converting rules to transfert object or
 * update rules from transfert object.
 *
 * @author Diogenes Firmiano
 */
@Local
public interface IRulesTOService {

  public RuleTO buildPublicRuleTO(Rule source);

  public void updateRuleEntity(Rule existing, RuleTO state, Application application) throws EntityNotFoundException, UnauthorizedException;
}
