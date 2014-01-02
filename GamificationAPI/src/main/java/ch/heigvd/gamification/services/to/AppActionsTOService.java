package ch.heigvd.gamification.services.to;

import ch.heigvd.gamification.services.to.interfaces.IAppActionsTOService;
import ch.heigvd.gamification.model.AppAction;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.to.AppActionTO;
import javax.ejb.Stateless;

/**
 * Implementation of the action transfert object management interface.
 *
 * @see IAppActionsTOService
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
  public void updateActionTypeEntity(AppAction existing, AppActionTO state, Application application) {
    existing.setTitle(state.getTitle());
    existing.setPoints(state.getPoints());
    existing.setDescription(state.getDescription());
    existing.setApplication(application);
  }
}
