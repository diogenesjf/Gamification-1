package ch.heigvd.gamification.services.crud;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Event;
import ch.heigvd.gamification.model.Success;
import ch.heigvd.gamification.services.crud.interfaces.IEventsManager;
import ch.heigvd.gamification.services.crud.interfaces.ISuccessesManager;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alexandre Perusset
 */
@Stateless
public class EventsManager implements IEventsManager {

  @PersistenceContext(unitName="Gamification")
  private EntityManager em;
  
  @EJB
  private ISuccessesManager successesManager;
  
  @Override
  public long create(Event eventData) {
    Event event = new Event(eventData);
    em.persist(event);
    event.getUser().addEvent(event);
    
    List<Success> newUserSuccesses = successesManager.findAllAcquiredByUser(event.getUser().getId());
    List<Success> userSuccesses = event.getUser().getSuccesses();

    for(Success success: newUserSuccesses)
    {
        if(!userSuccesses.contains(success))
            event.getUser().addSuccess(success);
    }
    
    return event.getId();
  }

  @Override
  public void delete(long id) throws EntityNotFoundException {
    em.remove(findById(id));
  }

  @Override
  public Event findById(long id) throws EntityNotFoundException {
    Event findEvent = em.find(Event.class, id);
    if (findEvent == null) {
      throw new EntityNotFoundException("Cannot find Event with id " + id);
    }
    return findEvent;
  }
  
  @Override
  public List<Event> findAll() {
    return em.createNamedQuery("findAllEvents").getResultList();
  }
  
}
