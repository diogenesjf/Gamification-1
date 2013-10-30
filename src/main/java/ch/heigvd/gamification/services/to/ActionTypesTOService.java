package ch.heigvd.gamification.services.to;

import ch.heigvd.gamification.services.to.interfaces.IActionTypesTOService;
import ch.heigvd.gamification.model.ActionType;
import ch.heigvd.gamification.to.PublicActionTypeTO;
import javax.ejb.Stateless;

/**
 *
 * @author Gaël Jobin
 */
@Stateless
public class ActionTypesTOService implements IActionTypesTOService {
     public PublicActionTypeTO buildPublicActionTypeTO(ActionType source) {
        PublicActionTypeTO to = new PublicActionTypeTO(source.getId(),source.getTitle(), source.getPoints(), source.getDescription());
        return to;
    }
    
    @Override
    public void updateActionTypeEntity(ActionType existingEntity, PublicActionTypeTO newState) {
        existingEntity.setTitle(newState.getTitle());
        existingEntity.setPoints(newState.getPoints());
        existingEntity.setDescription(newState.getDescription());
    }
    
}
