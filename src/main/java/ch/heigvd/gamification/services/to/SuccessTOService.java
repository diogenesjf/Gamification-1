package ch.heigvd.gamification.services.to;

import ch.heigvd.gamification.model.Success;
import ch.heigvd.gamification.services.to.interfaces.ISuccessTOService;
import ch.heigvd.gamification.to.SuccessTO;
import javax.ejb.Stateless;

/**
 *
 * @author
 */
@Stateless
public class SuccessTOService implements ISuccessTOService {

  @Override
  public SuccessTO buildUserTO(Success source) { //TODO
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void updateUserEntity(Success existingEntity, SuccessTO newState) { //TODO
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
