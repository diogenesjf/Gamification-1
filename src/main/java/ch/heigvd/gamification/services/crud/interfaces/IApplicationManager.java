/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.services.crud.interfaces;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Application;

/**
 *
 * @author thomasmoegli
 */
public interface IApplicationManager {
    
  public long create(Application applicationData);

  public void delete(long id) throws EntityNotFoundException;

  public Application findById(long id) throws EntityNotFoundException;
  
}
