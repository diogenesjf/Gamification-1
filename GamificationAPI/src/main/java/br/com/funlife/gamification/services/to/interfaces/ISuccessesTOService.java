package br.com.funlife.gamification.services.to.interfaces;

import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.model.Success;
import br.com.funlife.gamification.to.SuccessTO;
import javax.ejb.Local;

/**
 * This interface provides methods for converting successes to transfert object
 * or update successes from transfert object.
 *
 * @author Diogenes Firmiano
 */
@Local
public interface ISuccessesTOService {

  public SuccessTO buildSuccessTO(Success source);

  public void updateSuccessEntity(Success existingEntity, SuccessTO newState, Application application);
}
