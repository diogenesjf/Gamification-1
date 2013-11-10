package ch.heigvd.gamification.interceptors;

import ch.heigvd.gamification.model.Event;
import ch.heigvd.gamification.model.Success;
import ch.heigvd.gamification.services.crud.interfaces.ISuccessesManager;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author Gaël Jobin
 */
public class AppUserInterceptor {
    
     private static final Logger logger = Logger.getLogger("ch.heigvd.gamification.interceptors.AppUserInterceptor");
    @EJB
    ISuccessesManager successesManager;
    
    public AppUserInterceptor()
    {
        
    }
    
    @AroundInvoke
    public Object interceptAddEvent(InvocationContext ctx) throws Exception {
        System.out.println("AddUser Intercepted !");
        Object[] parameters = ctx.getParameters();
        Event event = (Event) parameters[0];
        List<Success> newUserSuccesses = successesManager.findAllAcquiredByUser(event.getUser().getId());
        List<Success> userSuccesses = event.getUser().getSuccesses();

        for(Success success: newUserSuccesses)
        {
            if(!userSuccesses.contains(success))
                event.getUser().addSuccess(success);
        }
        
         try {
            return ctx.proceed();
        } catch (Exception e) {
            logger.warning("Error calling ctx.proceed in modifyGreeting()");
            return null;
        }
    }
}

