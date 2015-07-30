package br.com.funlife.gamification.services.crud.interfaces;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.exceptions.UnauthorizedException;
import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.model.Success;
import java.util.List;
import javax.ejb.Local;

/**
 * This interface provides method to create, update, delete and get successes.
 *
 * @author Diogenes Firmiano
 */
@Local
public interface ISuccessesManager {

  public long create(Success successData);

  public void update(Success newState, Application app) throws EntityNotFoundException, UnauthorizedException;

  public void delete(long id, Application app) throws EntityNotFoundException, UnauthorizedException;

  public Success findById(long id, Application app) throws EntityNotFoundException, UnauthorizedException;

  public List<Success> findAll(Application app);

  public void checkRights(Success success, Application app) throws UnauthorizedException;
}
