package ch.heigvd.gamification.services.crud;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Success;
import ch.heigvd.gamification.services.crud.interfaces.local.IApplicationsManagerLocal;
import ch.heigvd.gamification.services.crud.interfaces.remote.IApplicationsManagerRemote;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gaël Jobin
 */
@Stateless
@Local(IApplicationsManagerLocal.class)
@Remote(IApplicationsManagerRemote.class)
public class ApplicationsManager implements IApplicationsManagerLocal, IApplicationsManagerRemote {

    @PersistenceContext(unitName = "Gamification")
    private EntityManager em;

    @Override
    public long create(Application applicationData) {
        Application newApplication = new Application(applicationData);
        em.persist(newApplication);
        return newApplication.getId();
    }

    @Override
    public void update(Application newState) throws EntityNotFoundException {
        Application applicationToUpdate = findById(newState.getId());
        em.merge(newState);
    }

    @Override
    public void delete(long id) throws EntityNotFoundException {
        Application applicationToDelete = findById(id);
        em.remove(applicationToDelete);
    }

    @Override
    public Application findById(long id) throws EntityNotFoundException {
        Application existingApplication = em.find(Application.class, id);
        if (existingApplication == null) {
            throw new EntityNotFoundException();
        }
        return existingApplication;
    }

    @Override
    public List<Application> findAll() {
        return em.createNamedQuery("findAllApplication").getResultList();

    }

}
