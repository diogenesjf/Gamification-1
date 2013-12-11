package ch.heigvd.gamification.services.to;

import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.services.to.interfaces.IApplicationsTOService;
import ch.heigvd.gamification.to.ApplicationTO;
import javax.ejb.Stateless;

/**
 *
 * @author Thomas Moegli
 */
@Stateless
public class ApplicationsTOService implements IApplicationsTOService {

  @Override
  public ApplicationTO buildPublicApplicationTO(Application source) {
    return new ApplicationTO(source.getId(), source.getName());
  }

  @Override
  public void updateApplicationEntity(Application existing, ApplicationTO state) {
    existing.setName(state.getName());
  }
}
