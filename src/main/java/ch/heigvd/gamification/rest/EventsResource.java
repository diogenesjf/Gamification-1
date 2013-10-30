package ch.heigvd.gamification.rest;

import ch.heigvd.gamification.model.Event;
import ch.heigvd.gamification.services.crud.interfaces.IEventsManager;
import ch.heigvd.gamification.services.to.interfaces.IEventsTOService;
import ch.heigvd.gamification.to.EventPublicTO;
import ch.heigvd.gamification.to.EventTO;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * REST Service
 *
 * @author Alexandre Perusset
 */
@Stateless
@Path("events")
public class EventsResource {

  @Context
  private UriInfo context;

  @EJB
  IEventsManager eventsManager;

  @EJB
  IEventsTOService eventsTOService;

  /**
   *
   * @return
   */
  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public List<EventPublicTO> getEvents() {
    List<EventPublicTO> events = new LinkedList<>();
    for(Event e : eventsManager.findAll()) {
      events.add(eventsTOService.buildPublicEventTO(e));
    }
    return events;
  }
  
  /**
   *
   * @param newEventTO
   * @return
   */
  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  public Response createEvent(EventTO newEventTO) {
    Event newEvent = new Event();
    eventsTOService.updateEventEntity(newEvent, newEventTO);
    return Response.created(
            context.getAbsolutePathBuilder().path(Long.toString(
                            eventsManager.create(newEvent)
                    )).build()
    ).build();
  }

}
