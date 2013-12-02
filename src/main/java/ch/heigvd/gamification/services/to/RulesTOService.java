package ch.heigvd.gamification.services.to;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.services.to.interfaces.IRulesTOService;
import ch.heigvd.gamification.model.Rule;
import ch.heigvd.gamification.services.crud.interfaces.local.IActionTypesManagerLocal;
import ch.heigvd.gamification.to.PublicRuleTO;
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
    IActionTypesManagerLocal actionTypesManager;
    
    public PublicRuleTO buildPublicRuleTO(Rule source) {
        PublicRuleTO to = new PublicRuleTO(source.getId(), source.getName(), source.getDescription(), source.getAcquiredPoints(), source.getActionType().getId());
        return to;
    }
    
    @Override
    public void updateRuleEntity(Rule existingEntity, PublicRuleTO newState) {
        try
        {
            existingEntity.setName(newState.getName());
            existingEntity.setDescription(newState.getDescription());
            existingEntity.setAcquiredPoints(newState.getAcquiredPoints());
            existingEntity.setActionType(actionTypesManager.findById(newState.getActionTypeId()));
        } catch (EntityNotFoundException ex) {
      Logger.getLogger(RulesTOService.class.getName()).log(Level.SEVERE, null, ex);
    }

    }
}
