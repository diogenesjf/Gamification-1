package ch.heigvd.gamification.services.crud;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.model.AppUser;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Event;
import ch.heigvd.gamification.model.Success;
import ch.heigvd.gamification.services.crud.interfaces.IAppUsersManager;
import ch.heigvd.gamification.services.crud.interfaces.IEventsManager;
import java.security.InvalidParameterException;
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

  @PersistenceContext(unitName = "Gamification")
  private EntityManager em;

  @EJB
  private IAppUsersManager usersManager;

  @Override
  public long create(Event event) throws EntityNotFoundException, UnauthorizedException {
    if ( event.getApplication() == null ) { //Check if application setted
      throw new InvalidParameterException("Cannot save an event without application");
    }
    Event newEvent = new Event(event);
    em.persist(newEvent);
    AppUser user = newEvent.getUser();
    user.addEvent(newEvent);
    //Add new success if needed
    for (Success success : usersManager.checkForNewSuccesses(newEvent.getUser())) {
      user.addSuccess(success);
    }
    usersManager.update(user, newEvent.getApplication());
    return newEvent.getId();
  }

  @Override
  public void delete(long id, Application application) throws EntityNotFoundException, UnauthorizedException {
    em.remove(findById(id, application));
  }

  @Override
  public Event findById(long id, Application application) throws EntityNotFoundException, UnauthorizedException {
    Event event = em.find(Event.class, id);
    if (event == null) {
      throw new EntityNotFoundException("Cannot find Event with id " + id);
    }
    checkRights(event, application);
    return event;
  }

  @Override
  public List<Event> findAll(Application application) {
    return em.createNamedQuery("findAllEvents")
            .setParameter("appid", application.getId())
            .getResultList();
  }

  @Override
  public void checkRights(Event event, Application app) throws UnauthorizedException {
    if (event.getApplication() == null || !event.getApplication().equals(app)) {
      throw new UnauthorizedException();
    }
  }
}
