package ch.heigvd.gamification.services.to.interfaces;

import ch.heigvd.gamification.model.Event;
import ch.heigvd.gamification.to.EventPublicTO;
import ch.heigvd.gamification.to.EventTO;
import javax.ejb.Local;

/**
 *
 * @author Alexandre Perusset
 */
@Local
public interface IEventsTOService {
  
  public EventTO buildEventTO(Event event);
  
  public EventPublicTO buildPublicEventTO(Event event);
  
  public void updateEventEntity(Event existingEntitiy, EventTO newState);
  
}
