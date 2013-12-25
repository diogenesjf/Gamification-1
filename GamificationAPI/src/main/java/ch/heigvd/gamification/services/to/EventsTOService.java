package ch.heigvd.gamification.services.to;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.model.AppAction;
import ch.heigvd.gamification.model.AppUser;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Event;
import ch.heigvd.gamification.services.crud.interfaces.IAppActionsManager;
import ch.heigvd.gamification.services.crud.interfaces.IAppUsersManager;
import ch.heigvd.gamification.services.to.interfaces.IEventsTOService;
import ch.heigvd.gamification.to.EventTO;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alexandre Perusset
 */
@Stateless
public class EventsTOService implements IEventsTOService {

  @EJB
  IAppUsersManager usersManager;
  
  @EJB
  IAppActionsManager actionTypesManager;
  
  @Override
  public EventTO buildEventTO(Event event) {
    return new EventTO(
            event.getId(),
            event.getUser().getId(),
            event.getActionType().getId(),
            event.getTimestamp()
    );
  }

  @Override
  public void updateEventEntity(Event existing, EventTO state, Application application) throws EntityNotFoundException, UnauthorizedException {
    try
    {
        existing.setUser(usersManager.findById(state.getUserId(), application));
        existing.setActionType(actionTypesManager.findById(state.getActionId(), application));
    }
    catch(UnauthorizedException e)
    {
        e.printStackTrace();
        throw e;
    }
    
    existing.setTimestamp(state.getTimestamp());
    existing.setApplication(application);
  }
}
