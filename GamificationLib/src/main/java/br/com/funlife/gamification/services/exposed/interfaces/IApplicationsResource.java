package br.com.funlife.gamification.services.exposed.interfaces;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.to.ApplicationTO;
import java.util.List;
import javax.ejb.Remote;
import javax.ws.rs.core.Response;

/**
 * Interface for applications management. The methods inside can be called
 * remotly or with jax-rs rest api.
 *
 * @author Diogenes Firmiano
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
