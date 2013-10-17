package ch.heigvd.gamification.services.to;

import ch.heigvd.gamification.model.UserAction;
import ch.heigvd.gamification.services.to.interfaces.IUserActionsTOService;
import ch.heigvd.gamification.to.UserActionTO;
import javax.ejb.Stateless;

/**
 *
 * @author
 */
@Stateless
public class UserActionsTOService implements IUserActionsTOService {

  @Override
  public UserActionTO buildUserActionTO(UserAction source) { //TODO
    return new UserActionTO(
            source.getId(),
            source.getTitle(),
            source.getDescription(),
            source.getPoints()
    );
  }

  @Override
  public void updateUserActionEntity(UserAction existingEntity, UserActionTO newState) { //TODO
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
