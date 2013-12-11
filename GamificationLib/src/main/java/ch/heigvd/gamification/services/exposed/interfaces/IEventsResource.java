package ch.heigvd.gamification.services.exposed.interfaces;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.to.EventPublicTO;
import ch.heigvd.gamification.to.EventTO;
import java.util.List;
import javax.ejb.Remote;
import javax.ws.rs.core.Response;

/**
 *
 * @author Alexandre Perusset
 */
@Remote
public interface IEventsResource {
  
  public List<EventPublicTO> getEvents(long idApp) throws EntityNotFoundException;
  
  public Response createEvent(EventTO newEventTO, long idApp) throws EntityNotFoundException, UnauthorizedException;
  
  public EventPublicTO getEventById(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;
}
