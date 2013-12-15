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
  
  public long createApplication(ApplicationTO applicationTO);
  
  public ApplicationTO getApplication(long id) throws EntityNotFoundException;
  
  public void updateApplication(ApplicationTO applicationTO, long id) throws EntityNotFoundException;
  
  public void deleteApplication(long id) throws EntityNotFoundException;
  
  public Response restCreateApplication(ApplicationTO applicationTO);
  
  public Response restUpdateApplication(ApplicationTO applicationTO, long id) throws EntityNotFoundException;
  
  public Response restDeleteApplication(long id) throws EntityNotFoundException;
}
