package ch.heigvd.gamification.rest.exceptionmappers;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Alexandre Perusset
 */
@Provider
public class NotFoundMapper implements ExceptionMapper<EntityNotFoundException> {

  @Override
  public Response toResponse(EntityNotFoundException exception) {
    exception.printStackTrace();
    return Response.status(Response.Status.NOT_FOUND).build();
  }

}
