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

  public UserActionTO buildUserActionTO(UserAction source);

  public void updateUserActionEntity(UserAction existingEntity, UserActionTO newState);
  
}
