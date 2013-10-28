/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package ch.heigvd.gamification.services.to;

import ch.heigvd.gamification.model.Rule;
import ch.heigvd.gamification.to.PublicRuleTO;
import javax.ejb.Stateless;

/**
 *
 * @author Gaël Jobin
 */
@Stateless
public class RulesTOService implements RulesTOServiceLocal {
    public PublicRuleTO buildPublicRuleTO(Rule source) {
        PublicRuleTO to = new PublicRuleTO(source.getId(), source.getName(), source.getDescription(), source.getAcquiredPoints());
        return to;
    }
    
    @Override
    public void updateRuleEntity(Rule existingEntity, PublicRuleTO newState) {
        existingEntity.setName(newState.getName());
        existingEntity.setDescription(newState.getDescription());
        existingEntity.setAcquiredPoints(newState.getAcquiredPoints());
    }
}
