package br.com.funlife.gamification.services.exposed.interfaces;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.exceptions.UnauthorizedException;
import br.com.funlife.gamification.to.EventTO;
import java.util.List;
import javax.ejb.Remote;
import javax.ws.rs.core.Response;

/**
 * Interface for events management. The methods inside can be called remotly or
 * with jax-rs rest api. It is not possible to update an existing event.
 *
 * @author Diogenes Firmiano
 */
@Remote
public interface IEventsResource {

  public long createEvent(EventTO newEventTO, long idApp) throws EntityNotFoundException, UnauthorizedException;

  public EventTO getEvent(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

  public List<EventTO> getEvents(long idApp) throws EntityNotFoundException;

  public Response restCreateEvent(EventTO newEventTO, long idApp) throws EntityNotFoundException, UnauthorizedException;
}
