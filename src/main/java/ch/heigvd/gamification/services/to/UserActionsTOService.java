package ch.heigvd.gamification.services.to;

import ch.heigvd.gamification.model.UserAction;
import ch.heigvd.gamification.services.to.interfaces.IUserActionsTOService;
import ch.heigvd.gamification.to.UserActionTO;

/**
 *
 * @author
 */
public class UserActionsTOService implements IUserActionsTOService {

  @Override
  public UserActionTO buildUserTO(UserAction source) { //TODO
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void updateUserEntity(UserAction existingEntity, UserActionTO newState) { //TODO
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
}
