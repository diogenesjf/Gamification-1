package ch.heigvd.gamificationclient;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.services.exposed.interfaces.IAppActionsResource;
import ch.heigvd.gamification.services.exposed.interfaces.IAppUsersResource;
import ch.heigvd.gamification.services.exposed.interfaces.IApplicationsResource;
import ch.heigvd.gamification.services.exposed.interfaces.IEventsResource;
import ch.heigvd.gamification.services.exposed.interfaces.ILeaderBoardsResource;
import ch.heigvd.gamification.services.exposed.interfaces.IRulesResource;
import ch.heigvd.gamification.services.exposed.interfaces.ISuccessesResource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * This Java Enterprise Application show how to remote connect to our
 * Gamification API from a JEE container (in the same virtual machine than the
 * API). Note that we assumed you have created at least one application into the
 * API.
 *
 * We need here dependencies to the GamificationLib Project, and to gf-client
 * 4.0 and javaee-api-7.0 through Maven Repositories.
 *
 * @author Alexandre Perusset
 */
public class Client {

  public static void main(String[] args) throws NamingException, EntityNotFoundException {

    //This is not needed to parametrize the context, because we are int the
    //same virtual machine (execution context).
    InitialContext ic = new InitialContext();

    IApplicationsResource appResource = (IApplicationsResource) ic.lookup("java:global/GamificationAPI/ApplicationsResource");
    System.out.println("Size of the application list : " + appResource.getApplications().size());

    IAppActionsResource actionResource = (IAppActionsResource) ic.lookup("java:global/GamificationAPI/AppActionsResource");
    System.out.println("Size of the action list : " + actionResource.getActions(1).size());

    IAppUsersResource userResource = (IAppUsersResource) ic.lookup("java:global/GamificationAPI/AppUsersResource");
    System.out.println("Size of the user list : " + userResource.getAllUsers(1).size());

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
