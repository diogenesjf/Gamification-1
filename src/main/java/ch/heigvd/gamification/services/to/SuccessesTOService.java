package ch.heigvd.gamification.services.to;

import ch.heigvd.gamification.model.Success;
import ch.heigvd.gamification.services.to.interfaces.ISuccessesTOService;
import ch.heigvd.gamification.to.SuccessTO;
import javax.ejb.Stateless;

/**
 *
 * @author Gaël Jobin
 */
@Stateless
public class SuccessesTOService implements ISuccessesTOService {

  @Override
  public SuccessTO buildSuccessTO(Success source) {
    return new SuccessTO(
            source.getId(),
            source.getName(),
            source.getBadge());
  }

  @Override
  public void updateSuccessEntity(Success existingEntity, SuccessTO newState) {
   existingEntity.setName(newState.getName());
   existingEntity.setBadge(newState.getBadge());
  }

}
