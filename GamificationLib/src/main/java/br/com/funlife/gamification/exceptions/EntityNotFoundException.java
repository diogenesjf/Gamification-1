package br.com.funlife.gamification.exceptions;

import javax.ejb.ApplicationException;

/**
 * Exception thrown when tyring to retrieve an inexisting entity.
 *
 * @author Diogenes Firmiano
 */
@ApplicationException(rollback = true)
public class EntityNotFoundException extends Exception {

  public EntityNotFoundException() {
  }

  public EntityNotFoundException(String message) {
    super(message);
  }
}
