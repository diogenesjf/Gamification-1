package ch.heigvd.gamification.services.exposed;

import ch.heigvd.gamification.rest.exceptionmappers.NotFoundMapper;
import ch.heigvd.gamification.rest.exceptionmappers.UnauthorizedMapper;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Register all the REST resources.
 * 
 * @author Alexandre Perusset
 */
@ApplicationPath("/")
public class RESTAPI extends Application {

  public static final String APP = "appid";
  
  @Override
  public Set<Class<?>> getClasses() {
    final Set<Class<?>> classes = new HashSet<>();
    classes.add(NotFoundMapper.class);
    classes.add(UnauthorizedMapper.class);
    classes.add(AppUsersResource.class);
    classes.add(SuccessesResource.class);
    classes.add(EventsResource.class);
    classes.add(LeaderBoardsResource.class);
    classes.add(RulesResource.class);
    classes.add(ApplicationsResource.class);
    classes.add(AppActionsResource.class);
    return classes;
  }
}
