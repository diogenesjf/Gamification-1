package br.com.funlife.gamification.services.to.interfaces;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.exceptions.UnauthorizedException;
import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.model.Event;
import br.com.funlife.gamification.to.EventTO;
import javax.ejb.Local;

/**
 * This interface provides methods for converting events to transfert object or
 * update events from transfert object.
 *
 * @author Diogenes Firmiano
 */
@Local
public interface IEventsTOService {

  public EventTO buildEventTO(Event event);

  public void updateEventEntity(Event existingEntitiy, EventTO newState, Application application) throws EntityNotFoundException, UnauthorizedException;
}
