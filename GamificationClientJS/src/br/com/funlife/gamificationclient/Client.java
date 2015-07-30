package br.com.funlife.gamificationclient;

import br.com.funlife.gamification.exceptions.EntityNotFoundException;
import br.com.funlife.gamification.services.exposed.interfaces.IAppActionsResource;
import br.com.funlife.gamification.services.exposed.interfaces.IAppUsersResource;
import br.com.funlife.gamification.services.exposed.interfaces.IApplicationsResource;
import br.com.funlife.gamification.services.exposed.interfaces.IEventsResource;
import br.com.funlife.gamification.services.exposed.interfaces.ILeaderBoardsResource;
import br.com.funlife.gamification.services.exposed.interfaces.IRulesResource;
import br.com.funlife.gamification.services.exposed.interfaces.ISuccessesResource;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * This Java Standard application show how to connect to our Gamification API
 * remote beans through JNDI in a different environment (without Glassfish
 * utilities). Note that we assumed you have created at least one application
 * into the API.
 *
 * We need here to add the library (jar) produced by the GamificaitionLib
 * project and so to gf-client.jar and javaee.jar from the glassfish4 lib
 * directory (this is not a maven project).
 *
 * @author Diogenes Firmiano
 */
public class Client {

  public static void main(String[] args) throws NamingException, EntityNotFoundException {

    Properties props = new Properties();

    props.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");

    props.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");

    props.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");

    InitialContext ic = new InitialContext(props);

    IApplicationsResource appResource = (IApplicationsResource) ic.lookup("java:global/GamificationAPI/ApplicationsResource");
    System.out.println("Size of the application list : " + appResource.getApplications().size());

    IAppActionsResource actionResource = (IAppActionsResource) ic.lookup("java:global/GamificationAPI/AppActionsResource");
    System.out.println("Size of the action list : " + actionResource.getActions(1).size());

    IAppUsersResource userResource = (IAppUsersResource) ic.lookup("java:global/GamificationAPI/AppUsersResource");
    System.out.println("Size of the user list : " + userResource.getAllUsers(1l).size());

    IEventsResource eventResource = (IEventsResource) ic.lookup("java:global/GamificationAPI/EventsResource");
    System.out.println("Size of the event list : " + eventResource.getEvents(1).size());

    ILeaderBoardsResource leaderboardResource = (ILeaderBoardsResource) ic.lookup("java:global/GamificationAPI/LeaderBoardsResource");
    System.out.println("Size of the leaderboard : " + leaderboardResource.getLeaderboard(1).size());

    IRulesResource ruleResource = (IRulesResource) ic.lookup("java:global/GamificationAPI/RulesResource");
    System.out.println("Size of the rule list : " + ruleResource.getAllRules(1).size());

    ISuccessesResource successResource = (ISuccessesResource) ic.lookup("java:global/GamificationAPI/SuccessesResource");
    System.out.println("Size of the success list : " + successResource.getSuccesses(1).size());
  }

}
