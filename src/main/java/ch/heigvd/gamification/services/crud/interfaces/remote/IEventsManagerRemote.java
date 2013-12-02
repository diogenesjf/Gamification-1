package ch.heigvd.gamification.services.crud.interfaces.remote;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
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

    public List<Event> findAll();

}
