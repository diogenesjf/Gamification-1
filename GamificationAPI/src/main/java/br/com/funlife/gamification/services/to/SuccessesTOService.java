package br.com.funlife.gamification.services.to;

import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.model.Success;
import br.com.funlife.gamification.services.to.interfaces.ISuccessesTOService;
import br.com.funlife.gamification.to.SuccessTO;
import javax.ejb.Stateless;

/**
 * Implementation of the success transfert object management interface.
 *
 * @see ISuccessesTOService
 * @author Diogenes Firmiano
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
  public void updateSuccessEntity(Success existing, SuccessTO state, Application application) {
    existing.setName(state.getName());
    existing.setBadge(state.getBadge());
    existing.setApplication(application);
  }
}
