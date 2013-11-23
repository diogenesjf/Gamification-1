package ch.heigvd.gamification.rest;

import ch.heigvd.gamification.rest.exceptionmappers.NotFoundMapper;
import ch.heigvd.gamification.interceptors.AppUserInterceptor;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author Olivier Liechti
 */
@ApplicationPath("/api")
public class RESTAPI extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    final Set<Class<?>> classes = new HashSet<>();
    // register root resources/providers
    classes.add(NotFoundMapper.class);
    classes.add(AppUserResource.class);
    classes.add(SuccessesResource.class);
    classes.add(EventsResource.class);
    classes.add(LeaderBoardResource.class);
    classes.add(RulesResource.class);
    classes.add(ActionTypesResource.class);
    return classes;
  }
}
