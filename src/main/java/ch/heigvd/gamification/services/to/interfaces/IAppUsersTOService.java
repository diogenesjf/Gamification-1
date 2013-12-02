package ch.heigvd.gamification.services.to.interfaces;

import ch.heigvd.gamification.model.AppUser;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.to.AppUserPublicTO;
import ch.heigvd.gamification.to.AppUserTO;
import ch.heigvd.gamification.to.RankedAppUserTO;
import javax.ejb.Local;

/**
 *
 * @author Alexandre Perusset
 */
@Local
public interface IAppUsersTOService {

  /**
   * This method builds a TO instance from a JPA entity instance. In this
   * particular case, the only thing that we do is get rid of the password
   * property (which is available in the JPA entity but should not be
   * communicated to clients).
   *
   * @param source the JPA entity
   * @return the AppUserPublicTO
   */
  public AppUserPublicTO buildPublicUserTO(AppUser source);
  
  public AppUserTO buildUserTO(AppUser source);
  
  public RankedAppUserTO buildRankedUserTO(AppUser source, Integer points);
  
  /**
   * This method updates an existing JPA entity by merging the state of the
   * provided TO instance. We do not touch the password property, but replace
   * the other properties.
   *
   * @param existingEntity the existing entity that we want to update
   * @param newState a TO that contains new state (subset of the entity state)
   * @param application the application of the user
   */
  public void updateUserEntity(AppUser existingEntity, AppUserTO newState, Application application);
   
}
