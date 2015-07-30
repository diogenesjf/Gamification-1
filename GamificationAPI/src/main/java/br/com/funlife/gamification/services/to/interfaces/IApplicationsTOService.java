package br.com.funlife.gamification.services.to.interfaces;

import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.to.ApplicationTO;
import javax.ejb.Local;

/**
 * This interface provides methods for converting applications to transfert
 * object or update applications from transfert object.
 *
 * @author Diogenes Firmiano
 */
@Local
public interface IApplicationsTOService {

  public ApplicationTO buildPublicApplicationTO(Application source);

  public void updateApplicationEntity(Application existing, ApplicationTO state);
}
