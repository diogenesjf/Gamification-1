package br.com.funlife.gamification.services.crud.interfaces;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.exceptions.UnauthorizedException;
import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.model.Event;
import java.util.List;
import javax.ejb.Local;

/**
 * This interface provides method to create, update, delete and get events.
 *
 * @author Diogenes Firmiano
 */
@Local
public interface IEventsManager {

  public long create(Event eventData) throws EntityNotFoundException, UnauthorizedException;

  public void delete(long id, Application application) throws EntityNotFoundException, UnauthorizedException;

  public Event findById(long id, Application application) throws EntityNotFoundException, UnauthorizedException;

  public List<Event> findAll(Application application);

  public void checkRights(Event event, Application app) throws UnauthorizedException;
}
