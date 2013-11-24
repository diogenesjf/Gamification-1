/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.services.to.interfaces;

import ch.heigvd.gamification.model.ActionType;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.to.PublicActionTypeTO;

/**
 *
 * @author thomasmoegli
 */
public interface IApplicationTOService {
    /**
     * This method builds a TO instance from a JPA entity instance. In this particular
     * case, the only thing that we do is get rid of the salary property (which is
     * available in the JPA entity but should not be communicated to clients).
     *
     * @param source the JPA entity
     * @return the TO
     */
    public PublicApplicationTO buildPublicActionTypeTO(Application source);
    
    /**
     * This method updates an existing JPA entity by merging the state of the
     * provided TO instance. We do not touch the salary property, but replace
     * the other properties.
     * @param existingEntity the existing entity that we want to update
     * @param newState a TO that contains new state (subset of the entity state)
     * @return the updated employee entity
     */
    public void updateActionTypeEntity(Application existingEntity, PublicApplicationTO newState);
}
