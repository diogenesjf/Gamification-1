package ch.heigvd.gamification.rest;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Alexandre Perusset
 */
public class GamificationRESTResource {
  
  @HeaderParam(value="apiid")
  private int apiID;
  
  @Context
  protected UriInfo context;
  
  /* TODO
  public Application getApplication() {
    
  }*/
  
}
