package ch.heigvd.gamification.services.to;

import javax.ejb.Stateless;
import ch.heigvd.gamification.model.AppUser;
import ch.heigvd.gamification.services.to.interfaces.IAppUsersTOService;
import ch.heigvd.gamification.to.AppUserTO;

/**
 *
 * @author Alexandre Perusset
 */
@Stateless
public class AppUsersTOService implements IAppUsersTOService {
  
  @Override
  public AppUserTO buildUserTO(AppUser source) {
    return new AppUserTO(
            source.getId(),
            source.getName(),
            source.getSurname(),
            source.getNickname()
    );
  }
  
  @Override
  public void updateUserEntity(AppUser existingEntity, AppUserTO newState) {
    existingEntity.setName(newState.getName());
    existingEntity.setSurname(newState.getSurname());
    existingEntity.setNickname(newState.getNickname());
  }
  
}
