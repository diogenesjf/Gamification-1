package br.com.funlife.gamification.services.to;

import br.com.funlife.gamification.services.to.interfaces.IAppActionsTOService;
import br.com.funlife.gamification.model.AppAction;
import br.com.funlife.gamification.model.AppActionPoint;
import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.services.crud.interfaces.IPointsManager;
import br.com.funlife.gamification.to.AppActionPointTO;
import br.com.funlife.gamification.to.AppActionTO;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Implementation of the action transfert object management interface.
 *
 * @see IAppActionsTOService
 * @author Diogenes Firmiano
 */
@Stateless
public class AppActionsTOService implements IAppActionsTOService {
 
    @Override
    public AppActionTO buildPublicActionTypeTO(AppAction source) {

        List<AppActionPointTO> pointsList = new LinkedList<>();
        if (source.getPointsList() != null) {
            for (AppActionPoint pointsList1 : source.getPointsList()) {
                AppActionPointTO actionPoint = new AppActionPointTO();
                actionPoint.setActionID(source.getId());
                actionPoint.setPointID(pointsList1.getPoint().getId());
                actionPoint.setPoints(pointsList1.getPoints());
                actionPoint.setId(pointsList1.getId());
                pointsList.add(actionPoint);
            }
        }
        return new AppActionTO(
                source.getId(),
                source.getTitle(),
                source.getPoints(),
                source.getDescription(),
                pointsList
        );
    }

    @Override
    public void updateActionTypeEntity(AppAction existing, AppActionTO state, Application application) {
        existing.setTitle(state.getTitle());
        existing.setPoints(state.getPoints());
        existing.setDescription(state.getDescription());
        existing.setApplication(application);
        
    }
}
