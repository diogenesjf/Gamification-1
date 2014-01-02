package ch.heigvd.gamification.services.to.interfaces;

import ch.heigvd.gamification.model.AppUser;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.to.AppUserPublicTO;
import ch.heigvd.gamification.to.AppUserTO;
import ch.heigvd.gamification.to.RankedAppUserTO;
import javax.ejb.Local;

/**
 * This interface provides methods for converting users to transfert object or
 * update users from transfert object.
 *
 * @author Alexandre Perusset
 */
@Local
public interface IAppUsersTOService {

  public AppUserPublicTO buildPublicUserTO(AppUser source);

  public AppUserTO buildUserTO(AppUser source);

  public RankedAppUserTO buildRankedUserTO(AppUser source, Integer points);

  public void updateUserEntity(AppUser existingEntity, AppUserTO newState, Application application);
}
