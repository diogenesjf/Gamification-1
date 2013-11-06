package ch.heigvd.gamification.services.crud;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Event;
import ch.heigvd.gamification.services.crud.interfaces.IEventsManager;
import java.util.List;
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
  
  @Override
  public long create(Event eventData) {
    Event event = new Event(eventData);
    em.persist(event);
    event.getUser().addEvent(event);
    //TODO ajouter l'event à la liste des events de ActionType
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
