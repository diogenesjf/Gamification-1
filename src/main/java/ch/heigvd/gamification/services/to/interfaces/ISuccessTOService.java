package ch.heigvd.gamification.services.to.interfaces;

import ch.heigvd.gamification.model.Success;
import ch.heigvd.gamification.to.SuccessTO;
import javax.ejb.Local;

/**
 *
 * @author
 */
@Local
public interface ISuccessTOService {

  public SuccessTO buildSuccessTO(Success source);

  public void updateSuccessEntity(Success existingEntity, SuccessTO newState);

}
