package ch.heigvd.gamification.services.to;

import javax.ejb.Stateless;
import ch.heigvd.gamification.model.AppUser;
import ch.heigvd.gamification.services.to.interfaces.IAppUsersTOService;
import ch.heigvd.gamification.to.AppUserPublicTO;
import ch.heigvd.gamification.to.AppUserTO;
import ch.heigvd.gamification.to.RankedAppUserTO;

/**
 *
 * @author Alexandre Perusset
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
  public void updateUserEntity(AppUser existingEntity, AppUserTO newState) {
    existingEntity.setName(newState.getName());
    existingEntity.setSurname(newState.getSurname());
    existingEntity.setNickname(newState.getNickname());
    existingEntity.setPassword(newState.getPassword());
  }

  @Override
  public RankedAppUserTO buildRankedUserTO(AppUser source, Long points) {
    return new RankedAppUserTO(
            source.getId(),
            points,
            source.getName(),
            source.getSurname(),
            source.getNickname()
    );
  }

}
