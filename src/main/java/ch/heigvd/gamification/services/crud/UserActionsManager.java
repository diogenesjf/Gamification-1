package ch.heigvd.gamification.services.crud;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.AppUser;
import ch.heigvd.gamification.model.UserAction;
import ch.heigvd.gamification.services.crud.interfaces.IUserActionsManager;
import java.util.List;

/**
 *
 * @author Alexandre Perusset
 */
public class UserActionsManager implements IUserActionsManager {

  @Override
  public long create(UserAction userActionData) { //TODO
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void update(UserAction newState) throws EntityNotFoundException { //TODO
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void delete(long id) throws EntityNotFoundException { //TODO
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public UserAction findById(long id) throws EntityNotFoundException { //TODO
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public List<UserAction> findAll() { //TODO
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public List<AppUser> findAllUsers() { //TODO
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
}
