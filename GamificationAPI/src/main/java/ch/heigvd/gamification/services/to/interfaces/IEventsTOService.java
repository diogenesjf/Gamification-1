package ch.heigvd.gamification.services.to.interfaces;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Event;
import ch.heigvd.gamification.to.EventTO;
import javax.ejb.Local;

/**
 * This interface provides methods for converting events to transfert object or
 * update events from transfert object.
 *
 * @author Alexandre Perusset
 */
@Local
public interface IEventsTOService {

  public EventTO buildEventTO(Event event);

  public void updateEventEntity(Event existingEntitiy, EventTO newState, Application application) throws EntityNotFoundException, UnauthorizedException;
}
