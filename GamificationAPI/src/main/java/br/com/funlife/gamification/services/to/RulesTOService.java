package br.com.funlife.gamification.services.to;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.exceptions.UnauthorizedException;
import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.services.to.interfaces.IRulesTOService;
import br.com.funlife.gamification.model.Rule;
import br.com.funlife.gamification.services.crud.interfaces.IAppActionsManager;
import br.com.funlife.gamification.to.RuleTO;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Implementation of the rule transfert object management interface.
 *
 * @see IRulesTOService
 * @author Diogenes Firmiano
 */
@Stateless
public class RulesTOService implements IRulesTOService {

  @EJB
  IAppActionsManager actionsManager;

  @Override
  public RuleTO buildPublicRuleTO(Rule source) {
    return new RuleTO(
            source.getId(),
            source.getName(),
            source.getDescription(),
            source.getGoalPoints(),
            source.getAction().getId()
    );
  }

  /**
   *
   * @param existing
   * @param state
   * @param application
   * @throws EntityNotFoundException if the action does not exists
   * @throws UnauthorizedException action does not belong to application
   */
  @Override
  public void updateRuleEntity(Rule existing, RuleTO state, Application application) throws EntityNotFoundException, UnauthorizedException {
    existing.setName(state.getName());
    existing.setDescription(state.getDescription());
    existing.setGoalPoints(state.getGoalPoints());
    existing.setAction(actionsManager.findById(state.getActionID(), application));
    existing.setApplication(application);
  }
}
