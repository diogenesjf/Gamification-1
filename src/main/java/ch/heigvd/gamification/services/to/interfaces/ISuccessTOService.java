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

  public SuccessTO buildUserTO(Success source);

  public void updateUserEntity(Success existingEntity, SuccessTO newState);

}
