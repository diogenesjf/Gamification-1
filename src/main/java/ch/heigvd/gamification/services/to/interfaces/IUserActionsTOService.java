package ch.heigvd.gamification.services.to.interfaces;

import ch.heigvd.gamification.model.UserAction;
import ch.heigvd.gamification.to.UserActionTO;
import javax.ejb.Local;

/**
 *
 * @author
 */
@Local
public interface IUserActionsTOService {

  public UserActionTO buildUserTO(UserAction source);

  public void updateUserEntity(UserAction existingEntity, UserActionTO newState);
  
}
