package br.com.funlife.gamification.services.to;

import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.services.to.interfaces.IApplicationsTOService;
import br.com.funlife.gamification.to.ApplicationTO;
import javax.ejb.Stateless;

/**
 * Implementation of the application transfert object management interface.
 *
 * @see IApplicationsTOService
 * @author Diogenes Firmiano
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
