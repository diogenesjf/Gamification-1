package ch.heigvd.gamification.rest;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.services.crud.interfaces.local.IApplicationsManagerLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * Stateless Bean that represent an abstract Gamification resource. That
 * provides a method to get the application of the current context. The
 * application id is passed as an header parameter.
 *
 * @author Alexandre Perusset
 */
@Stateless
public class GamificationRESTResource {

  @HeaderParam(value = "appid")
  private long appID;
  
  private Application application = null;

  @Context
  protected UriInfo context;

  @EJB
  IApplicationsManagerLocal securityManager;

  public Application getApplication() throws EntityNotFoundException {
    try {
      if ( this.application == null ) //Prevent from multiple access
        application = securityManager.findById(appID);
      return application;
    } catch (EntityNotFoundException e) {
      throw new EntityNotFoundException("Bad Header param, cannot found Application with id " + appID);
    }
  }
}
