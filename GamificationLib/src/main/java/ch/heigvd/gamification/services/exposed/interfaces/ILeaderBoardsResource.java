package ch.heigvd.gamification.services.exposed.interfaces;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.to.RankedAppUserTO;
import java.util.List;
import javax.ejb.Remote;

/**
 * Interface for leaderboard management. The method inside can be called remotly
 * or with jax-rs rest api.
 *
 * @author Alexandre Perusset
 */
@Remote
public interface ILeaderBoardsResource {

  public List<RankedAppUserTO> getLeaderboard(long idApp) throws EntityNotFoundException;
}
