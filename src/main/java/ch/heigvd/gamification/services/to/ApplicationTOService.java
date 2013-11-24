/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.services.to;

import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.to.ApplicationTO;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ch.heigvd.gamification.services.crud.interfaces.IApplicationManager;


/**
 *
 * @author thomasmoegli
 */
public class ApplicationTOService {

    
    public ApplicationTO buildApplicationTO(Application source) {
        return new ApplicationTO(source.);
    }
    
   
}
