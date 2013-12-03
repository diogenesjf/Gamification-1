package ch.heigvd.gamification.services.to.interfaces;

import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.to.ApplicationTO;
import javax.ejb.Local;

/**
 *
 * @author Thomas Moegli
 */
@Local
public interface IApplicationsTOService {

  public ApplicationTO buildPublicApplicationTO(Application source);

  public void updateApplicationEntity(Application existing, ApplicationTO state);
}
