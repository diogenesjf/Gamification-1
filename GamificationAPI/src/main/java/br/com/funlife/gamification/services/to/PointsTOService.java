package br.com.funlife.gamification.services.to;

import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.model.Point;
import br.com.funlife.gamification.services.to.interfaces.IPointsTOService;
import br.com.funlife.gamification.to.PointTO;
import javax.ejb.Stateless;

/**
 * Implementation of the point transfert object management interface.
 *
 * @see IPointesTOService
 * @author Diogenes Firmiano
 */
@Stateless
public class PointsTOService implements IPointsTOService {

  @Override
  public PointTO buildPointTO(Point source) {
    return new PointTO(
            source.getId(),
            source.getTitle(),
            source.getIcon(),
            source.getDescription());
  }

  @Override
  public void updatePointEntity(Point existing, PointTO state, Application application) {
    existing.setTitle(state.getTitle());
    existing.setIcon(state.getIcon());
    existing.setDescription(state.getDescription());
    existing.setMoneyEquivalence(state.getMoneyEquivalence());
    existing.setApplication(application);
  }
}
