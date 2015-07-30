package br.com.funlife.gamification.services.to.interfaces;

import br.com.funlife.gamification.model.AppAction;
import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.to.AppActionTO;
import javax.ejb.Local;

/**
 * This interface provides methods for converting actions to transfert object or
 * update actions from transfert object.
 *
 * @author Diogenes Firmiano
 */
@Local
public interface IAppActionsTOService {

  public AppActionTO buildPublicActionTypeTO(AppAction source);

  public void updateActionTypeEntity(AppAction existing, AppActionTO state, Application application);
}
