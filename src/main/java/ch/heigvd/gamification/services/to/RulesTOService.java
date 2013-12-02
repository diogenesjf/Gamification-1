package ch.heigvd.gamification.services.to;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.services.to.interfaces.IRulesTOService;
import ch.heigvd.gamification.model.Rule;
import ch.heigvd.gamification.services.crud.interfaces.local.IAppActionsManagerLocal;
import ch.heigvd.gamification.to.RuleTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Gaël Jobin
 */
@Stateless
public class RulesTOService implements IRulesTOService {
    
    @EJB
    IAppActionsManagerLocal actionTypesManager;
    
    public RuleTO buildPublicRuleTO(Rule source) {
        RuleTO to = new RuleTO(source.getId(), source.getName(), source.getDescription(), source.getAcquiredPoints(), source.getAction().getId());
        return to;
    }
    
    @Override
    public void updateRuleEntity(Rule existingEntity, RuleTO newState) {
        try
        {
            existingEntity.setName(newState.getName());
            existingEntity.setDescription(newState.getDescription());
            existingEntity.setAcquiredPoints(newState.getAcquiredPoints());
            existingEntity.setAction(actionTypesManager.findById(newState.getActionTypeId()));
        } catch (EntityNotFoundException ex) {
      Logger.getLogger(RulesTOService.class.getName()).log(Level.SEVERE, null, ex);
    }

    }
}
