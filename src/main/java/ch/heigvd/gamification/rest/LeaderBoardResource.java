package ch.heigvd.gamification.rest;

import ch.heigvd.gamification.model.AppUser;
import ch.heigvd.gamification.services.to.interfaces.IAppUsersTOService;
import ch.heigvd.gamification.to.RankedAppUserTO;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Service
 * 
 * @author Alexandre Perusset
 */
@Stateless
@Path("leaderboard")
public class LeaderBoardResource {
  
  @PersistenceContext(unitName="Gamification")
  private EntityManager em;
  
  @EJB
  private IAppUsersTOService usersTOService;
  
  /**
   *
   * @return
   */
  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public List<RankedAppUserTO> getLeaderboard() {
    List<RankedAppUserTO> result = new LinkedList<>();
    List<Object[]> users = em.createQuery("getRankedUsers").getResultList();
    for(Object[] rankedUser : users) {
      result.add(usersTOService.buildRankedUserTO((AppUser)rankedUser[0], (Long)rankedUser[1]));
    }
    return result;
  }
  
  
  
}
