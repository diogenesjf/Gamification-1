/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.heigvd.gamification.services.crud;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.ActionType;
import ch.heigvd.gamification.model.Rule;
import ch.heigvd.gamification.model.Success;
import ch.heigvd.gamification.model.UserAction;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gaël Jobin
 */
@Stateless
public class ActionTypesManager implements ActionTypesManagerLocal {
    
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
        List actionTypes = em.createNamedQuery("findAllActionType").getResultList();
        return actionTypes;
    }
    
    @Override
    public List<UserAction> findAllActions() { //TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Rule> findAllRules() { //TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
