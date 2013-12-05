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

    public long create(Event eventData) throws EntityNotFoundException, UnauthorizedException;

    public void delete(long id, Application application) throws EntityNotFoundException, UnauthorizedException;

    public Event findById(long id, Application application) throws EntityNotFoundException, UnauthorizedException;

    public List<Event> findAll(Application application);

    public void checkRights(Event event, Application app) throws UnauthorizedException;
}
