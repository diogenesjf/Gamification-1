package ch.heigvd.gamification.services.to;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Event;
import ch.heigvd.gamification.services.crud.interfaces.IAppUsersManager;
import ch.heigvd.gamification.services.to.interfaces.IEventsTOService;
import ch.heigvd.gamification.to.EventPublicTO;
import ch.heigvd.gamification.to.EventTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Alexandre Perusset
 */
@Stateless
public class EventsTOService implements IEventsTOService {

  @EJB
  IAppUsersManager usersManager;
  
  @Override
  public EventTO buildEventTO(Event event) {
    return new EventTO(
            event.getUser().getId(),
            event.getActionType().getId(),
            event.getTimestamp()
    );
  }

  @Override
  public EventPublicTO buildPublicEventTO(Event event) {
    return new EventPublicTO(
        event.getId(),
        event.getUser().getName(),
        event.getActionType().getName(),
        event.getTimestamp()
    );
  }

  @Override
  public void updateEventEntity(Event existingEntity, EventTO newState) {
    try {
      existingEntity.setUser(usersManager.findById(newState.getUserId()));
      //TODO setActionType
      existingEntity.setTimestamp(newState.getTimestamp());
    } catch (EntityNotFoundException ex) {
      Logger.getLogger(EventsTOService.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
