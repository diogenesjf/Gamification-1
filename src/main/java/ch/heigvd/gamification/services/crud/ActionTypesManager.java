package ch.heigvd.gamification.services.crud;

import ch.heigvd.gamification.services.crud.interfaces.local.IActionTypesManagerLocal;
import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.ActionType;
import ch.heigvd.gamification.model.Event;
import ch.heigvd.gamification.model.Rule;
import ch.heigvd.gamification.services.crud.interfaces.remote.IActionTypesManagerRemote;
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
@Local(IActionTypesManagerLocal.class)
@Remote(IActionTypesManagerRemote.class)
public class ActionTypesManager implements IActionTypesManagerLocal, IActionTypesManagerRemote {

    @PersistenceContext(unitName = "Gamification")
    private EntityManager em;

    @Override
    public long create(ActionType actionTypeData) {
        ActionType newActionType = new ActionType(actionTypeData);
        em.persist(newActionType);
        return newActionType.getId();
    }

    @Override
    public void update(ActionType newState) throws EntityNotFoundException {
        ActionType actionTypeToUpdate = findById(newState.getId());
        em.merge(newState);
    }

    @Override
    public void delete(long id) throws EntityNotFoundException {
        ActionType actionTypeToDelete = findById(id);
        em.remove(actionTypeToDelete);
    }

    @Override
    public ActionType findById(long id) throws EntityNotFoundException {
        ActionType existingActionType = em.find(ActionType.class, id);
        if (existingActionType == null) {
            throw new EntityNotFoundException();
        }
        return existingActionType;
    }

    @Override
    public List<ActionType> findAll() {
        List actionTypes = em.createNamedQuery("findAllActionTypes").getResultList();
        return actionTypes;
    }

    @Override
    public List<Event> findAllActions() { //TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Rule> findAllRules() { //TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
