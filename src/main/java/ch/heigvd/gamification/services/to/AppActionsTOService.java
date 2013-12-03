package ch.heigvd.gamification.services.to;

import ch.heigvd.gamification.services.to.interfaces.IAppActionsTOService;
import ch.heigvd.gamification.model.AppAction;
import ch.heigvd.gamification.to.AppActionTO;
import javax.ejb.Stateless;

/**
 *
 * @author Gaël Jobin
 */
@Stateless
public class AppActionsTOService implements IAppActionsTOService {

  @Override
  public AppActionTO buildPublicActionTypeTO(AppAction source) {
    return new AppActionTO(
            source.getId(),
            source.getTitle(),
            source.getPoints(),
            source.getDescription()
    );
  }

  @Override
  public void updateActionTypeEntity(AppAction existingEntity, AppActionTO newState) {
    existingEntity.setTitle(newState.getTitle());
    existingEntity.setPoints(newState.getPoints());
    existingEntity.setDescription(newState.getDescription());
  }
}
