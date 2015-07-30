package br.com.funlife.gamification.services.to.interfaces;

import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.model.Point;
import br.com.funlife.gamification.to.PointTO;
import javax.ejb.Local;

/**
 * This interface provides methods for converting pointes to transfert object
 * or update pointes from transfert object.
 *
 * @author Diogenes Firmiano
 */
@Local
public interface IPointsTOService {

  public PointTO buildPointTO(Point source);

  public void updatePointEntity(Point existingEntity, PointTO newState, Application application);
}
