package ch.heigvd.gamification.services.to;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.AppAction;
import ch.heigvd.gamification.model.AppUser;
import ch.heigvd.gamification.model.Event;
import ch.heigvd.gamification.services.crud.interfaces.local.IAppActionsManagerLocal;
import ch.heigvd.gamification.services.crud.interfaces.local.IAppUsersManagerLocal;
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
  IAppUsersManagerLocal usersManager;
  
  @EJB
  IAppActionsManagerLocal actionTypesManager;
  
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
    AppUser u = event.getUser();
    AppAction at = event.getActionType();
    return new EventPublicTO(
        event.getId(),
        u.getName() + " " + u.getSurname() + " (" + u.getNickname() + ")",
        at.getDescription(),
        event.getTimestamp(),
        at.getPoints()
    );
  }

  @Override
  public void updateEventEntity(Event existingEntity, EventTO newState) {
    try {
      existingEntity.setUser(usersManager.findById(newState.getUserId()));
      existingEntity.setActionType(actionTypesManager.findById(newState.getActionTypeId()));
      existingEntity.setTimestamp(newState.getTimestamp());
    } catch (EntityNotFoundException ex) {
      Logger.getLogger(EventsTOService.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
