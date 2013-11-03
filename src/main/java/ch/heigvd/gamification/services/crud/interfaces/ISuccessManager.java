package ch.heigvd.gamification.services.crud.interfaces;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.AppUser;
import ch.heigvd.gamification.model.Success;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexandre Perusset
 */
@Local
public interface ISuccessManager {
  
  public long create(Success successData);

  public void update(Success newState) throws EntityNotFoundException;

  public void delete(long id) throws EntityNotFoundException;

  public Success findById(long id) throws EntityNotFoundException;

  public List<Success> findAll();  
}
