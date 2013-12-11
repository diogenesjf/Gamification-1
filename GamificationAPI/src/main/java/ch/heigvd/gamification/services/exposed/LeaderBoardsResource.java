package ch.heigvd.gamification.services.exposed;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.AppUser;
import ch.heigvd.gamification.services.crud.interfaces.IApplicationsManager;
import ch.heigvd.gamification.services.exposed.interfaces.ILeaderBoardsResource;
import ch.heigvd.gamification.services.to.interfaces.IAppUsersTOService;
import ch.heigvd.gamification.to.RankedAppUserTO;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Service. Expose some service to get the leader board of the application.
 * The leader board is the users ranked by total of acquired points.
 * 
 * @author Alexandre Perusset
 */
@Stateless
@Path("leaderboard")
public class LeaderBoardsResource implements ILeaderBoardsResource {
      
  @PersistenceContext(unitName="Gamification")
  private EntityManager em;
  
  @EJB
  private IAppUsersTOService usersTOService;
  
  @EJB
  private IApplicationsManager appManager;
  
  /**
   * Provides the leader board of the current application.
   * 
   * @param idApp id of the application
   * @return List<RankedAppUserTO> the list of ranked users
   * @throws EntityNotFoundException application does not exists
   */
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  @Override
  public List<RankedAppUserTO> getLeaderboard(@HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException {
    //TODO Bad, query is not checked at compilation time
    String query =  "select u, coalesce(sum(a.points), 0) as points "
                  + "from AppUser u "
                    + "left join u.events e "
                    + "left join e.action a "
                  + "where u.application.id = " + appManager.findById(idApp).getId() + " "
                  + "group by u "
                  + "order by points desc";
    List<RankedAppUserTO> result = new LinkedList<>();
    List<Object[]> users = em.createQuery(query).getResultList();
    for(Object[] rankedUser : users) {
      result.add(usersTOService.buildRankedUserTO((AppUser)rankedUser[0], (Integer)rankedUser[1]));
    }
    return result;
  }
}
