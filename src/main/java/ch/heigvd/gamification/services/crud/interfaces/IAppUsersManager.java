package ch.heigvd.gamification.services.crud.interfaces;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.AppUser;
import ch.heigvd.gamification.model.Success;
import ch.heigvd.gamification.model.UserAction;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexandre perusset
 */
@Local
public interface IAppUsersManager {

  public long create(AppUser userData);

  public void update(AppUser newState) throws EntityNotFoundException;

  public void delete(long id) throws EntityNotFoundException;

  public AppUser findById(long id) throws EntityNotFoundException;

  public List<AppUser> findAll();
  
  public List<Success> findAllSuccess();
  
  public List<UserAction> findAllUserActions();
}