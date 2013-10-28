/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.heigvd.gamification.services.to;

import ch.heigvd.gamification.model.ActionType;
import ch.heigvd.gamification.to.PublicActionTypeTO;
import javax.ejb.Stateless;

/**
 *
 * @author Gaël Jobin
 */
@Stateless
public class ActionTypesTOService implements ActionTypesTOServiceLocal {
     public PublicActionTypeTO buildPublicActionTypeTO(ActionType source) {
        PublicActionTypeTO to = new PublicActionTypeTO(source.getId(),source.getName());
        return to;
    }
    
    @Override
    public void updateActionTypeEntity(ActionType existingEntity, PublicActionTypeTO newState) {
        existingEntity.setName(newState.getName());
    }
    
}
