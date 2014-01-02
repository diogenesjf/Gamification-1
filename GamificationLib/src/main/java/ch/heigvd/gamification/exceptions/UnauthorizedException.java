package ch.heigvd.gamification.exceptions;

import javax.ejb.ApplicationException;

/**
 * Exception thrown when trying to access an entity of another application thant
 * the current application (id of the application is passed as an http header).
 *
 * @author Alexandre Perusset
 */
@ApplicationException(rollback = true)
public class UnauthorizedException extends Exception {

  public UnauthorizedException() {
  }

  public UnauthorizedException(String message) {
    super(message);
  }
}
