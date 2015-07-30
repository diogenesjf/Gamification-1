package br.com.funlife.gamification.services.exposed.interfaces;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.exceptions.UnauthorizedException;
import br.com.funlife.gamification.to.PointTO;
import java.util.List;
import javax.ejb.Remote;
import javax.ws.rs.core.Response;

/**
 * Interface for points management. The methods inside can be called remotly
 * or with jax-rs rest api.
 *
 * @author Diogenes Firmiano
 */
@Remote
public interface IPointsResource {

  public long createPoint(PointTO pointTO, long idApp) throws EntityNotFoundException;

  public void updatePoint(PointTO pointTO, long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

  public void deletePoint(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

  public List<PointTO> getPoints(long idApp) throws EntityNotFoundException;

  public PointTO getPoint(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

  public Response restCreatePoint(PointTO pointTO, long idApp) throws EntityNotFoundException;

  public Response restUpdatePoint(PointTO pointTO, long id, long idApp) throws EntityNotFoundException, UnauthorizedException;

  public Response restDeletePoint(long id, long idApp) throws EntityNotFoundException, UnauthorizedException;
}
