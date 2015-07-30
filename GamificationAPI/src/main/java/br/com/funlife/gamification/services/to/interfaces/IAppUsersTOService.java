package br.com.funlife.gamification.services.to.interfaces;

import br.com.funlife.gamification.model.AppUser;
import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.to.AppUserPublicTO;
import br.com.funlife.gamification.to.AppUserTO;
import br.com.funlife.gamification.to.RankedAppUserTO;
import javax.ejb.Local;

/**
 * This interface provides methods for converting users to transfert object or
 * update users from transfert object.
 *
 * @author Diogenes Firmiano
 */
@Local
public interface IAppUsersTOService {

  public AppUserPublicTO buildPublicUserTO(AppUser source);

  public AppUserTO buildUserTO(AppUser source);

  public RankedAppUserTO buildRankedUserTO(AppUser source, Integer points);

  public void updateUserEntity(AppUser existingEntity, AppUserTO newState, Application application);
}
