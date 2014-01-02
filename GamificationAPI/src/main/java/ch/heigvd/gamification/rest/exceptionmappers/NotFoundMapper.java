package ch.heigvd.gamification.rest.exceptionmappers;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Allow to map EntityNotFoundException to an error page.
 *
 * @author Alexandre Perusset
 */
@Provider
public class NotFoundMapper implements ExceptionMapper<EntityNotFoundException> {

  @Override
  public Response toResponse(EntityNotFoundException exception) {
    return Response.status(Response.Status.NOT_FOUND).build();
  }
}
