package br.com.funlife.gamification.services.crud;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.exceptions.UnauthorizedException;
import br.com.funlife.gamification.model.AppUser;
import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.model.Event;
import br.com.funlife.gamification.model.Success;
import br.com.funlife.gamification.services.crud.interfaces.IAppUsersManager;
import br.com.funlife.gamification.services.crud.interfaces.IEventsManager;
import java.security.InvalidParameterException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Implementation of the events manager interface.
 *
 * @see IEventsManager
 * @author Diogenes Firmiano
 */
@Stateless
public class EventsManager implements IEventsManager {

  @PersistenceContext(unitName = "Gamification")
  private EntityManager em;

  @EJB
  private IAppUsersManager usersManager;

  @Override
  public long create(Event event) throws EntityNotFoundException, UnauthorizedException {
    if (event.getApplication() == null) { //Check if application setted
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
