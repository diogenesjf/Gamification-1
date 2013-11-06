package ch.heigvd.gamification.rest;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * REST Service mettant à disposition les ressources liées aux événements
 * pouvant se produire dans l'application.
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
   * Obtenir la liste complète (ordonnée de du plus récent au plus ancien) des
   * événements ayant eu lieu dans l'application.
   *
   * @return List<EventPublicTO> liste des événements
   */
  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public List<EventPublicTO> getEvents() {
    List<EventPublicTO> events = new LinkedList<>();
    for (Event e : eventsManager.findAll()) {
      events.add(eventsTOService.buildPublicEventTO(e));
    }
    return events;
  }

  /**
   * Ajouter un événement à l'application.
   *
   * @param newEventTO les données du nouvel énévement
   * @return Response HTTP Code 201 Created
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

  /**
   * Obtenir les informations d'un événement particulié.
   *
   * @param id identifiant unique de l'événement
   * @return EventPublicTO l'évenement voulu
   * @throws EntityNotFoundException événement inexistant
   */
  @GET
  @Path("{id}")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public EventPublicTO getEventById(@PathParam("id") long id) throws EntityNotFoundException {
    return eventsTOService.buildPublicEventTO(eventsManager.findById(id));
  }
}
