package br.com.funlife.gamification.services.exposed;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.exceptions.UnauthorizedException;
import br.com.funlife.gamification.model.Application;
import br.com.funlife.gamification.model.Point;
import br.com.funlife.gamification.services.crud.interfaces.IApplicationsManager;
import br.com.funlife.gamification.services.crud.interfaces.IPointsManager;
import br.com.funlife.gamification.services.exposed.interfaces.IPointsResource;
import br.com.funlife.gamification.services.to.interfaces.IPointsTOService;
import br.com.funlife.gamification.to.PointTO;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * REST and Remote Service. Expose some service for pointes management. A point
 * is defined by a name and a badge (an image).
 *
 * @author Diogenes Firmiano
 */
@Stateless
@Path("points")
public class PointsResource implements IPointsResource {

    @Context
    private UriInfo context;

    @EJB
    private IPointsManager pointManager;

    @EJB
    private IPointsTOService pointTOService;

    @EJB
    private IApplicationsManager appManager;

    /**
     * Creates a new instance of PointResource
     */
    public PointsResource() {
    }

    /**
     * Creates a new Point resource from the provided representation.
     *
     * @param pointTO the representation of the new point
     * @param idApp id of the application
     * @return Response HTTP Code 201 Created
     * @throws EntityNotFoundException application does not exists
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Override
    public Response restCreatePoint(PointTO pointTO, @HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException {
        return Response.created(
                context.getAbsolutePathBuilder().path(Long.toString(
                                createPoint(pointTO, idApp)
                        )).build()
        ).build();
    }

    @Override
    public long createPoint(PointTO pointTO, long idApp) throws EntityNotFoundException {
        Point point = new Point();
        pointTOService.updatePointEntity(point, pointTO, appManager.findById(idApp));
        return pointManager.create(point);
    }

    /**
     * Retrieves a representation of a list of Point resources.
     *
     * @param idApp id of the application
     * @return List<PointTO> an list of PointTO
     * @throws EntityNotFoundException application does not exists
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public List<PointTO> getPoints(@HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException {
        List<PointTO> result = new LinkedList<>();
        for (Point singlePoint : pointManager.findAll(appManager.findById(idApp))) {
            result.add(pointTOService.buildPointTO(singlePoint));
        }
        return result;
    }

    /**
     * Retrieves representation of a Point resource.
     *
     * @param id the unique id of the point
     * @param idApp id of the application
     * @return an instance of PointTO
     * @throws EntityNotFoundException point or application does not exists
     * @throws UnauthorizedException point does not belong current application
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public PointTO getPoint(@PathParam("id") long id, @HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException, UnauthorizedException {
        return pointTOService.buildPointTO(pointManager.findById(id, appManager.findById(idApp)));
    }

    /**
     * Update a Point resource by passing his new representation.
     *
     * @param pointTO new representation
     * @param id the unique id of the point
     * @param idApp id of the application
     * @return Response HTTP Code 204 No Content
     * @throws EntityNotFoundException point or application not found
     * @throws UnauthorizedException point does not belong application
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Override
    public Response restUpdatePoint(PointTO pointTO, @PathParam("id") long id, @HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException, UnauthorizedException {
        updatePoint(pointTO, id, idApp);
        return Response.noContent().build();
    }

    @Override
    public void updatePoint(PointTO pointTO, long id, long idApp) throws EntityNotFoundException, UnauthorizedException {
        Application app = appManager.findById(idApp);
        Point pointToUpdate = pointManager.findById(id, app);
        pointTOService.updatePointEntity(pointToUpdate, pointTO, app);
        pointManager.update(pointToUpdate, app);
    }

    /**
     * Delete an Point resource by passing his id.
     *
     * @param id the unique id of the point to delete
     * @param idApp id of the application
     * @return Response HTTP Code 204 No Content
     * @throws EntityNotFoundException point not found
     * @throws UnauthorizedException point does not belong application
     */
    @DELETE
    @Path("{id}")
    @Override
    public Response restDeletePoint(@PathParam("id") long id, @HeaderParam(value = RESTAPI.APP) long idApp) throws EntityNotFoundException, UnauthorizedException {
        deletePoint(id, idApp);
        return Response.noContent().build();
    }

    @Override
    public void deletePoint(long id, long idApp) throws EntityNotFoundException, UnauthorizedException {
        pointManager.delete(id, appManager.findById(idApp));
    }

}
