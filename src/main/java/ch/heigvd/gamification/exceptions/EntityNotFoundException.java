package ch.heigvd.gamification.exceptions;

import javax.ejb.ApplicationException;

/**
 *
 * @author Olivier Liechti
 */
@ApplicationException(rollback = true)
public class EntityNotFoundException extends Exception {

  public EntityNotFoundException() {
  }

  public EntityNotFoundException(String message) {
    super(message);
  }

}
