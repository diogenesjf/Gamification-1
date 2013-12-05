package ch.heigvd.gamification.rest;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.model.Event;
import ch.heigvd.gamification.services.crud.interfaces.local.IEventsManagerLocal;
import ch.heigvd.gamification.services.crud.interfaces.local.ISuccessesManagerLocal;
import ch.heigvd.gamification.services.to.interfaces.IEventsTOService;
import ch.heigvd.gamification.to.EventPublicTO;
import ch.heigvd.gamification.to.EventTO;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Service. Expose service for events management. An event is an action
 * performed by a user at a given time.
 *
 * @author Alexandre Perusset
 */
@Path("events")
public class EventsResource extends GamificationRESTResource {

  @EJB
  IEventsManagerLocal eventsManager;

  @EJB
  ISuccessesManagerLocal successesManager;

  @EJB
  IEventsTOService eventsTOService;

  /**
   * Get the list of the events that occurred in the current application.
   *
   * @return List<EventPublicTO> a list of EventPublicTO
   * @throws EntityNotFoundException application does not exists
   */
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public List<EventPublicTO> getEvents() throws EntityNotFoundException {
    List<EventPublicTO> events = new LinkedList<>();
    for (Event e : eventsManager.findAll(getApplication())) {
      events.add(eventsTOService.buildPublicEventTO(e));
    }
    return events;
  }

  /**
   * Add an event to the application by passing his representation.
   *
   * @param newEventTO new event representation
   * @return Response HTTP Code 201 Created
   * @throws EntityNotFoundException application does not exists
   * @throws UnauthorizedException event's user does not belong application
   */
  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  public Response createEvent(EventTO newEventTO) throws EntityNotFoundException, UnauthorizedException {
    Event newEvent = new Event();
    eventsTOService.updateEventEntity(newEvent, newEventTO, getApplication());
    return Response.created(
            context.getAbsolutePathBuilder().path(Long.toString(
                            eventsManager.create(newEvent)
                    )).build()
    ).build();
  }

  /**
   * Get the informations of an event.
   *
   * @param id unique id of the event
   * @return EventPublicTO the event representation
   * @throws EntityNotFoundException the event or application does not exists
   * @throws UnauthorizedException the event does not belong current application
   */
  @GET
  @Path("{id}")
  @Produces({MediaType.APPLICATION_JSON})
  public EventPublicTO getEventById(@PathParam("id") long id) throws EntityNotFoundException, UnauthorizedException {
    return eventsTOService.buildPublicEventTO(eventsManager.findById(id, getApplication()));
  }
}
