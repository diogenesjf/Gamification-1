package ch.heigvd.gamification.services.to.interfaces;

import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Success;
import ch.heigvd.gamification.to.SuccessTO;
import javax.ejb.Local;

/**
 * This interface provides methods for converting successes to transfert object
 * or update successes from transfert object.
 *
 * @author Alexandre Perusset
 */
@Local
public interface ISuccessesTOService {

  public SuccessTO buildSuccessTO(Success source);

  public void updateSuccessEntity(Success existingEntity, SuccessTO newState, Application application);
}
