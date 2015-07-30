package br.com.funlife.gamification.services.to.interfaces;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.exceptions.UnauthorizedException;
import br.com.funlife.gamification.model.AppActionPoint;
import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.to.AppActionPointTO;
import javax.ejb.Local;

/**
 * This interface provides methods for converting actions to transfert object or
 * update actions from transfert object.
 *
 * @author Diogenes Firmiano
 */
@Local
public interface IAppActionPointsTOService {

  public AppActionPointTO buildPublicActionPointTypeTO(AppActionPoint source);

  public void updateActionPointTypeEntity(AppActionPoint existing, AppActionPointTO state, Application application) throws EntityNotFoundException, UnauthorizedException;
}
