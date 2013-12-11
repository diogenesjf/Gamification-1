package ch.heigvd.gamification.exceptions;

import javax.ejb.ApplicationException;

/**
 *
 * @author Olivier Liechti
 */
@ApplicationException(rollback = true)
public class UnauthorizedException extends Exception {

  public UnauthorizedException() {
  }

  public UnauthorizedException(String message) {
    super(message);
  }

}
