package br.com.funlife.gamification.rest.exceptionmappers;

import br.com.funlife.gamification.exceptions.UnauthorizedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Allow to map UnauthorizedException to an error page.
 *
 * @author Diogenes Firmiano
 */
@Provider
public class UnauthorizedMapper implements ExceptionMapper<UnauthorizedException> {

  @Override
  public Response toResponse(UnauthorizedException exception) {
    return Response.status(Response.Status.UNAUTHORIZED).build();
  }
}
