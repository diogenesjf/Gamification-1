package br.com.funlife.gamification.services.to;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.exceptions.UnauthorizedException;
import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.model.Event;
import br.com.funlife.gamification.services.crud.interfaces.IAppActionsManager;
import br.com.funlife.gamification.services.crud.interfaces.IAppUsersManager;
import br.com.funlife.gamification.services.to.interfaces.IEventsTOService;
import br.com.funlife.gamification.to.EventTO;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Implementation of the event transfert object management interface.
 *
 * @see IEventsTOService
 * @author Diogenes Firmiano
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
    try {
      existing.setUser(usersManager.findById(state.getUserId(), application));
      existing.setActionType(actionTypesManager.findById(state.getActionId(), application));
    } catch (UnauthorizedException e) {
      e.printStackTrace();
      throw e;
    }

    existing.setTimestamp(state.getTimestamp());
    existing.setApplication(application);
  }
}
