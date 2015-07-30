package br.com.funlife.gamification.services.to;

import javax.ejb.Stateless;
import br.com.funlife.gamification.model.AppUser;
import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.services.to.interfaces.IAppUsersTOService;
import br.com.funlife.gamification.to.AppUserPublicTO;
import br.com.funlife.gamification.to.AppUserTO;
import br.com.funlife.gamification.to.RankedAppUserTO;

/**
 * Implementation of the user transfert object management interface.
 *
 * @see IAppUsersTOService
 * @author Diogenes Firmiano
 */
@Stateless
public class AppUsersTOService implements IAppUsersTOService {

  @Override
  public AppUserPublicTO buildPublicUserTO(AppUser source) {
    return new AppUserPublicTO(
            source.getId(),
            source.getName(),
            source.getSurname(),
            source.getNickname()
    );
  }

  @Override
  public AppUserTO buildUserTO(AppUser source) {
    return new AppUserTO(
            source.getId(),
            source.getName(),
            source.getSurname(),
            source.getNickname(),
            source.getPassword()
    );
  }

  @Override
  public void updateUserEntity(AppUser existing, AppUserTO newState, Application application) {
    existing.setName(newState.getName());
    existing.setSurname(newState.getSurname());
    existing.setNickname(newState.getNickname());
    existing.setPassword(newState.getPassword());
    existing.setApplication(application);
  }

  @Override
  public RankedAppUserTO buildRankedUserTO(AppUser source, Integer points) {
    return new RankedAppUserTO(
            source.getId(),
            points,
            source.getName(),
            source.getSurname(),
            source.getNickname()
    );
  }
}
