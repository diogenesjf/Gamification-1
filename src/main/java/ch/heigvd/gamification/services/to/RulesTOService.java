package ch.heigvd.gamification.services.to;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.services.to.interfaces.IRulesTOService;
import ch.heigvd.gamification.model.Rule;
import ch.heigvd.gamification.services.crud.interfaces.local.IAppActionsManagerLocal;
import ch.heigvd.gamification.to.RuleTO;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Gaël Jobin
 */
@Stateless
public class RulesTOService implements IRulesTOService {

  @EJB
  IAppActionsManagerLocal actionsManager;

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
   */
  @Override
  public void updateRuleEntity(Rule existing, RuleTO state, Application application) throws EntityNotFoundException {
    existing.setName(state.getName());
    existing.setDescription(state.getDescription());
    existing.setGoalPoints(state.getGoalPoints());
    existing.setAction(actionsManager.findById(state.getActionID()));
    existing.setApplication(application);
  }
}
