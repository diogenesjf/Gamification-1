package ch.heigvd.gamification.services.exposed.interfaces;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.to.ApplicationTO;
import java.util.List;
import javax.ejb.Remote;
import javax.ws.rs.core.Response;

/**
 *
 * @author Alexandre Perusset
 */
@Remote
public interface IApplicationsResource {
  
  public List<ApplicationTO> getApplications();
  
  public Response createApplication(ApplicationTO applicationTO);
  
  public ApplicationTO getApplication(long id) throws EntityNotFoundException;
  
  public Response updateApplication(ApplicationTO applicationTO, long id) throws EntityNotFoundException;
  
  public Response deleteApplication(long id) throws EntityNotFoundException;
}
