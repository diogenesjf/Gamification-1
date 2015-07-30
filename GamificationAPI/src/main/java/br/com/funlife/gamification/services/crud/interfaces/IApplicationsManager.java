package br.com.funlife.gamification.services.crud.interfaces;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.model.Application;
import java.util.List;
import javax.ejb.Local;

/**
 * This interface provides method to create, update, delete and get
 * applications.
 *
 * @author Diogenes Firmiano
 */
@Local
public interface IApplicationsManager {

  public long create(Application applicationData);

  public void update(Application newState) throws EntityNotFoundException;

  public void delete(long id) throws EntityNotFoundException;

  public Application findById(long id) throws EntityNotFoundException;

  public List<Application> findAll();
}
