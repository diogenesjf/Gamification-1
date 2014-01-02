package ch.heigvd.gamification.services.exposed;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.exceptions.UnauthorizedException;
import ch.heigvd.gamification.model.Event;
import ch.heigvd.gamification.services.crud.interfaces.IApplicationsManager;
import ch.heigvd.gamification.services.crud.interfaces.IEventsManager;
import ch.heigvd.gamification.services.exposed.interfaces.IEventsResource;
import ch.heigvd.gamification.services.to.interfaces.IEventsTOService;
import ch.heigvd.gamification.to.EventTO;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * REST and Remote Service. Expose service for events management. An event is an
 * action performed by a user at a given time.
 *
 * @author Alexandre Perusset
 */
@Stateless
@Path("events")
public class EventsResource implements IEventsResource {

  @Context
  private UriInfo context;

  @EJB
  private IEventsManager eventsManager;

  @EJB
  private IEventsTOService eventsTOService;

  @EJB
  private IApplicationsManager appManager;

  /**
   * Get the list of the events that occurred in the current application.
   *
   * @param idApp id of the application
   * @return List<EventTO> a list of EventPublicTO
   * @throws EntityNotFoundException application does not exists
   */
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  @Override
  public List<EventTO> getEvents(@HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException {
    List<EventTO> events = new LinkedList<>();
    for (Event e : eventsManager.findAll(appManager.findById(idApp))) {
      events.add(eventsTOService.buildEventTO(e));
    }
    return events;
  }

  /**
   * Add an event to the application by passing his representation.
   *
   * @param newEventTO new event representation
   * @param idApp id of the application
   * @return Response HTTP Code 201 Created
   * @throws EntityNotFoundException application does not exists
   * @throws UnauthorizedException event's user does not belong application
   */
  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  @Override
  public Response restCreateEvent(EventTO newEventTO, @HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException, UnauthorizedException {
    return Response.created(
            context.getAbsolutePathBuilder().path(Long.toString(
                            createEvent(newEventTO, idApp)
                    )).build()
    ).build();
  }

  @Override
  public long createEvent(EventTO newEventTO, long idApp) throws EntityNotFoundException, UnauthorizedException {
    Event newEvent = new Event();
    eventsTOService.updateEventEntity(newEvent, newEventTO, appManager.findById(idApp));
    return eventsManager.create(newEvent);
  }

  /**
   * Get the informations of an event.
   *
   * @param id unique id of the event
   * @param idApp id of the application
   * @return EventTO the event representation
   * @throws EntityNotFoundException the event or application does not exists
   * @throws UnauthorizedException the event does not belong current application
   */
  @GET
  @Path("{id}")
  @Produces({MediaType.APPLICATION_JSON})
  @Override
  public EventTO getEvent(@PathParam("id") long id, @HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException, UnauthorizedException {
    return eventsTOService.buildEventTO(eventsManager.findById(id, appManager.findById(idApp)));
  }
}
