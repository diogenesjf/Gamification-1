package br.com.funlife.gamification.services.to;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.exceptions.UnauthorizedException;
import br.com.funlife.gamification.model.AppActionPoint;
import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.services.crud.interfaces.IAppActionsManager;
import br.com.funlife.gamification.services.crud.interfaces.IPointsManager;
import br.com.funlife.gamification.services.to.interfaces.IAppActionPointsTOService;
import br.com.funlife.gamification.to.AppActionPointTO;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Implementation of the action point transfert object management interface.
 *
 * @see IAppActionPointsTOService
 * @author Diogenes Firmiano
 */
@Stateless
public class AppActionPointsTOService implements IAppActionPointsTOService {
 
  @EJB 
  IAppActionsManager actionsManager;
  
  @EJB
  IPointsManager pointsManager;

  @Override
    public AppActionPointTO buildPublicActionPointTypeTO(AppActionPoint source) {

        return new AppActionPointTO(
                source.getId(),
                source.getAction().getId(),
                source.getPoint().getId(),
                source.getPoints()
        );
    }

    @Override
    public void updateActionPointTypeEntity(AppActionPoint existing, AppActionPointTO state, Application application) throws EntityNotFoundException, UnauthorizedException {
        existing.setId(state.getId());
        existing.setPoints(state.getPoints());
        existing.setAction(actionsManager.findById(state.getActionID(), application));
        existing.setPoint(pointsManager.findById(state.getPointID(), application));
        
    }
}
