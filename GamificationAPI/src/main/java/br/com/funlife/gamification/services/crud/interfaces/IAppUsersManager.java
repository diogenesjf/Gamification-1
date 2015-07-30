package br.com.funlife.gamification.services.crud.interfaces;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.exceptions.UnauthorizedException;
import br.com.funlife.gamification.model.AppUser;
import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.model.Success;
import java.util.List;
import javax.ejb.Local;

/**
 * This interface provides method to create, update, delete and get users. It is
 * also possible to get the user successes or check if user has obtained new
 * successes.
 *
 * @author Diogenes Firmiano
 */
@Local
public interface IAppUsersManager {

  public long create(AppUser user);

  public void update(AppUser state, Application app) throws EntityNotFoundException, UnauthorizedException;

  public void delete(long id, Application app) throws EntityNotFoundException, UnauthorizedException;

  public AppUser findById(long id, Application app) throws EntityNotFoundException, UnauthorizedException;

  public List<AppUser> findAll(Application app);

  public List<AppUser> findAllBySuccess(Success success, Application app) throws UnauthorizedException;

  public void checkRights(AppUser user, Application app) throws UnauthorizedException;

  public List<Success> checkForNewSuccesses(AppUser user);
}
