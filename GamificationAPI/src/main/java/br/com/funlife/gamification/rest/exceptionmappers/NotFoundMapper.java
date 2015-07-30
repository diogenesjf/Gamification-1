package br.com.funlife.gamification.rest.exceptionmappers;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Allow to map EntityNotFoundException to an error page.
 *
 * @author Diogenes Firmiano
 */
@Provider
public class NotFoundMapper implements ExceptionMapper<EntityNotFoundException> {

  @Override
  public Response toResponse(EntityNotFoundException exception) {
    return Response.status(Response.Status.NOT_FOUND).build();
  }
}
