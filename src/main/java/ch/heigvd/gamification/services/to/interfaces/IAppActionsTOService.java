package ch.heigvd.gamification.services.to.interfaces;

import ch.heigvd.gamification.model.AppAction;
import ch.heigvd.gamification.to.AppActionTO;
import javax.ejb.Local;

/**
 *
 * @author Gaël Jobin
 */
@Local
public interface IAppActionsTOService {

  public AppActionTO buildPublicActionTypeTO(AppAction source);

  public void updateActionTypeEntity(AppAction existingEntity, AppActionTO newState);
}
