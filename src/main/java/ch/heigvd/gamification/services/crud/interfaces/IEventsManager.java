package ch.heigvd.gamification.services.crud.interfaces;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Event;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexandre Perusset
 */
@Local
public interface IEventsManager {
  
  public long create(Event eventData);

  public void delete(long id) throws EntityNotFoundException;

  public Event findById(long id) throws EntityNotFoundException;
  
  public List<Event> findAll();
  
}
