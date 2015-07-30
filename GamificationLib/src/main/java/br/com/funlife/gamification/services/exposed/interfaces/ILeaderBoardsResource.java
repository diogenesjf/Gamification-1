package br.com.funlife.gamification.services.exposed.interfaces;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.to.RankedAppUserTO;
import java.util.List;
import javax.ejb.Remote;

/**
 * Interface for leaderboard management. The method inside can be called remotly
 * or with jax-rs rest api.
 *
 * @author Diogenes Firmiano
 */
@Remote
public interface ILeaderBoardsResource {

  public List<RankedAppUserTO> getLeaderboard(long idApp) throws EntityNotFoundException;
}
