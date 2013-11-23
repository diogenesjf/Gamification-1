package ch.heigvd.gamification.rest.exceptionmappers;

import ch.heigvd.gamification.exceptions.UnauthorizedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Alexandre Perusset
 */
@Provider
public class UnauthorizedMapper implements ExceptionMapper<UnauthorizedException> {

  @Override
  public Response toResponse(UnauthorizedException exception) {
    return Response.status(Response.Status.UNAUTHORIZED).build();
  }

}
