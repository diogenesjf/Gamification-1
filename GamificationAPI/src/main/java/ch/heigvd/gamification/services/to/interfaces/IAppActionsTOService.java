package ch.heigvd.gamification.services.to.interfaces;

import ch.heigvd.gamification.model.AppAction;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.to.AppActionTO;
import javax.ejb.Local;

/**
 * This interface provides methods for converting actions to transfert object or
 * update actions from transfert object.
 *
 * @author Gaël Jobin
 */
@Local
public interface IAppActionsTOService {

  public AppActionTO buildPublicActionTypeTO(AppAction source);

  public void updateActionTypeEntity(AppAction existing, AppActionTO state, Application application);
}
