package ch.heigvd.gamification.services.to;

import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.services.to.interfaces.IApplicationsTOService;
import ch.heigvd.gamification.to.PublicApplicationTO;
import javax.ejb.Stateless;


/**
 *
 * @author thomasmoegli
 */
@Stateless
public class ApplicationsTOService implements IApplicationsTOService{

    
    public PublicApplicationTO buildPublicApplicationTO(Application source) {
        return new PublicApplicationTO(source.getId(),
                                 source.getName());
    }
    
    @Override
  public void updateApplicationEntity(Application existingEntity, PublicApplicationTO newState) {
   existingEntity.setName(newState.getName());
  }
}
