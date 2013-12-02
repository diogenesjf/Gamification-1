package ch.heigvd.gamification.services.to;

import ch.heigvd.gamification.services.to.interfaces.IActionTypesTOService;
import ch.heigvd.gamification.model.AppAction;
import ch.heigvd.gamification.to.AppActionTO;
import javax.ejb.Stateless;

/**
 *
 * @author Gaël Jobin
 */
@Stateless
public class ActionTypesTOService implements IActionTypesTOService {
     public AppActionTO buildPublicActionTypeTO(AppAction source) {
        AppActionTO to = new AppActionTO(source.getId(),source.getTitle(), source.getPoints(), source.getDescription());
        return to;
    }
    
    @Override
    public void updateActionTypeEntity(AppAction existingEntity, AppActionTO newState) {
        existingEntity.setTitle(newState.getTitle());
        existingEntity.setPoints(newState.getPoints());
        existingEntity.setDescription(newState.getDescription());
    }
    
}
