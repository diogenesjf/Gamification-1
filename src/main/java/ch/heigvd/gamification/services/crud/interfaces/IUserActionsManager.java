package ch.heigvd.gamification.services.crud.interfaces;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.AppUser;
import ch.heigvd.gamification.model.UserAction;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexandre Perusset
 */
@Local
public interface IUserActionsManager {
  
  public long create(UserAction userActionData);

  public void update(UserAction newState) throws EntityNotFoundException;

  public void delete(long id) throws EntityNotFoundException;

  public UserAction findById(long id) throws EntityNotFoundException;

  public List<UserAction> findAll();
  
  public List<AppUser> findAllUsers();
}
