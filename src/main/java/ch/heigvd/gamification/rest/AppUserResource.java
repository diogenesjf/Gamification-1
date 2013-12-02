package ch.heigvd.gamification.rest;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.AppUser;
import ch.heigvd.gamification.model.Event;
import ch.heigvd.gamification.model.Success;
import ch.heigvd.gamification.services.crud.interfaces.local.IAppUsersManagerLocal;
import ch.heigvd.gamification.services.to.interfaces.IAppUsersTOService;
import ch.heigvd.gamification.services.to.interfaces.IEventsTOService;
import ch.heigvd.gamification.services.to.interfaces.ISuccessesTOService;
import ch.heigvd.gamification.to.AppUserPublicTO;
import ch.heigvd.gamification.to.AppUserTO;
import ch.heigvd.gamification.to.EventPublicTO;
import ch.heigvd.gamification.to.SuccessTO;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Service mettant à disposition les ressources nécessaire pour la gestion
 * des utilisateurs de l'api.
 *
 * @author Alexandre Perusset
 */
@Stateless
@Path("users")
public class AppUserResource extends GamificationRESTResource {

    @EJB
    IAppUsersManagerLocal usersManager;

    @EJB
    IAppUsersTOService usersTOService;

    @EJB
    ISuccessesTOService successesTOService;

    @EJB
    IEventsTOService eventsTOService;

    public AppUserResource() {
    }

    /**
     * Obtenir la liste des utilisateurs.
     *
     * @return List<AppUserPublicTO> liste des utilisateurs
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<AppUserPublicTO> getAllUsers() {
        List<AppUserPublicTO> result = new LinkedList<>();
        for (AppUser user : usersManager.findAll()) {
            result.add(usersTOService.buildPublicUserTO(user));
        }
        return result;
    }

    /**
     * Créer un nouvel utilisateur.
     *
     * @param newUserTO le nouvel utilisateur
     * @return Response HTTP Code 201 Created
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createUser(AppUserTO newUserTO) {
        AppUser newUser = new AppUser();
        usersTOService.updateUserEntity(newUser, newUserTO);
        return Response.created(
                context.getAbsolutePathBuilder().path(Long.toString(
                                usersManager.create(newUser)
                        )).build()
        ).build();
    }

    /**
     * Obtenir un des utilisateurs de l'application.
     *
     * @param id identifiant unique de l'utilisateur
     * @return AppUserPublicTO utilisateur voulu
     * @throws EntityNotFoundException utilisateur non trouvé
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public AppUserPublicTO getUser(@PathParam("id") long id) throws EntityNotFoundException {
        return usersTOService.buildPublicUserTO(usersManager.findById(id));
    }

    /**
     * Mettre à jour un utilisateur.
     *
     * @param updatedUserTO nouvelle données de l'utilisateur
     * @param id identifiant unique de l'utilisateur
     * @return Response HTTP Code 204 No Content
     * @throws EntityNotFoundException utilisateur non trouvé
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateUser(AppUserTO updatedUserTO, @PathParam("id") long id) throws EntityNotFoundException {
        AppUser userToUpdate = usersManager.findById(id);
        usersTOService.updateUserEntity(userToUpdate, updatedUserTO);
        usersManager.update(userToUpdate);
        return Response.noContent().build();
    }

    /**
     * Supprimer un utilisateur.
     *
     * @param id identifiant unique de l'utilisateur
     * @return Response HTTP Code 2014 No Content
     * @throws EntityNotFoundException utilisateur non trouvé
     */
    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") long id) throws EntityNotFoundException {
        usersManager.delete(id);
        return Response.noContent().build();
    }

    /**
     * Obtenir la liste (avec aucun ordre particulié) des succès obtenu par un
     * utilisateur particulié.
     *
     * @param id identifiant unique de l'utilisateur
     * @return List<SuccessTO> liste des succès obtenus
     * @throws EntityNotFoundException utilisateur non trouvé
     */
    @GET
    @Path("{id}/success")
    public List<SuccessTO> getUserSuccesses(@PathParam("id") long id) throws EntityNotFoundException {
        //If we want another order, use a parametrized NamedQuery
        List<SuccessTO> successTO = new LinkedList<>();
        for (Success success : usersManager.findById(id).getSuccesses()) {
            successTO.add(successesTOService.buildSuccessTO(success));
        }
        return successTO;
    }

    /**
     * Obtenir la liste (sans ordre particulié) des événements générés par un
     * utilisateur particulié.
     *
     * @param id identifiant unique de l'utilisateur
     * @return List<EventPublicTO> liste des énévements générés
     * @throws EntityNotFoundException utilisateur non trouvé
     */
    @GET
    @Path("{id}/events")
    public List<EventPublicTO> getUserEvents(@PathParam("id") long id) throws EntityNotFoundException {
        //If we want another order, use a parametrized NamedQuery
        List<EventPublicTO> eventsTO = new LinkedList<>();
        for (Event event : usersManager.findById(id).getEvents()) {
            eventsTO.add(eventsTOService.buildPublicEventTO(event));
        }
        return eventsTO;
    }
}
