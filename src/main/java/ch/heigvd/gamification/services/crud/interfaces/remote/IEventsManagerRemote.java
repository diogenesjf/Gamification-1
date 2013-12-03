package ch.heigvd.gamification.services.crud.interfaces.remote;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Event;
import java.util.List;

/**
 *
 * @author Alexandre Perusset
 */
public interface IEventsManagerRemote {

    public long create(Event eventData);

    public void delete(long id) throws EntityNotFoundException;

    public Event findById(long id) throws EntityNotFoundException;

    public List<Event> findAll(Application application);

    public void checkRights(long id, Application app) throws EntityNotFoundException, UnauthorizedException;
}
