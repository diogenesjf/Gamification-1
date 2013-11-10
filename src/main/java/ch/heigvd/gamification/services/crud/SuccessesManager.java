package ch.heigvd.gamification.services.crud;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Success;
import ch.heigvd.gamification.services.crud.interfaces.ISuccessesManager;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alexandre Perusset
 */
@Stateless
public class SuccessesManager implements ISuccessesManager {

  @PersistenceContext(unitName = "Gamification")
  private EntityManager em;
     
  @Override
  public long create(Success successData) {
      Success newSuccess = new Success(successData);
      em.persist(newSuccess);
      return newSuccess.getId();
  }

  @Override
  public void update(Success newState) throws EntityNotFoundException {
    Success successToUpdate = findById(newState.getId());
    em.merge(newState);
  }

  @Override
  public void delete(long id) throws EntityNotFoundException {
      Success successToDelete = findById(id);
      em.remove(successToDelete);
  }

  @Override
  public Success findById(long id) throws EntityNotFoundException {
      Success existingSuccess = em.find(Success.class, id);
      if(existingSuccess == null)
          throw new EntityNotFoundException();
      return existingSuccess;
  }

  @Override
  public List<Success> findAll() {
        return em.createNamedQuery("findAllSuccess").getResultList();
  }
  
  /**
   * Retrieve all Success acquired by an User.
   * This method will NOT use the link between User and Success, it will search
   * for Events belonging to the User and compute the amount of points acquired
   * by ActionType to identify all the passed Success.
   * 
   * 
   * @param id
   * @return 
   */
  @Override
  public List<Success> findAllAcquiredByUser(long id)
  {
      /**
       * Meaning of this query.
       * 
       * Part 1:
       * The query will take each ActionType and calculate the amount of points
       * needed (based on the Rules attached to this ActionType) to achieve a
       * Success. The result will be a list containing each Success id and for each
       * of them, the amount of points needed in a specific ActionType to pass
       * the Succcess.
       * 
       * Part 2:
       * The query will search for a given user, all the events and will compute
       * (based on the ActionType of each Event) the amount of points the user
       * has accumulated for each ActionType.
       * 
       * Part 3:
       * With a left join, the query will retrieve a list of Success id and for
       * each of them an extra information (1 or 0). To sum up, this extra information
       * represent, for a Success, if a set of rules has been passed or not. A set
       * of rules represent the rules that are related to the same ActionType for
       * a same Success.
       * 
       * Part 4:
       * The query will retrieve the passed Successes for a given User. Based on 
       * the previous query, it will verify if, for a Success, each set of rules
       * have been passed or not. For this, it will compare the number of different
       * ActionType for a Success with the number of passed group-of-rules.
       * 
       */
      String query =        // Part4
                            "SELECT finalReq.SuccessId as ID " +
                            "FROM " +
                                // Part 3
                                "(SELECT SuccessId, CASE WHEN accumulatedPoints >= neededPoints THEN 1 ELSE 0 END as RulesPassed " +
                                "FROM " +
                                    // Part 1
                                    "(SELECT s.ID as SuccessID, SUM(r.ACQUIREDPOINTS) as neededPoints, r.ACTIONTYPE_ID as ActionTypeID " +
                                    "FROM SUCCESS as s, SUCCESS_RULE as sr, RULE as r " +
                                    "WHERE s.ID = sr.SUCCESS_ID and r.ID = sr.RULES_ID " +
                                    "GROUP BY r.ACTIONTYPE_ID, s.ID) as req1 " +
                                "LEFT JOIN " +
                                    // Part 2
                                    "(SELECT a.id as ActionTypeID, SUM(a.points) as accumulatedPoints " +
                                    "FROM ActionType as a, Event as e " +
                                    "WHERE a.ID = e.actionType_ID AND e.user_Id = ? " +
                                    "GROUP BY a.id) as req2 " +
                                "ON req1.ActionTypeID = req2.ActionTypeID) as finalReq " +
                            "GROUP BY finalReq.SuccessID " +
                            "HAVING COUNT(*) = SUM(finalReq.RulesPassed)";
        
      
      
        List<Long> listObject = em.createNativeQuery(query).setParameter(1, id).getResultList();
        List<Success> listSuccess = new ArrayList<Success>();
        
        for (Long result : listObject)
        {  
            try
            {
                System.out.println("ID Success: "+result);
                listSuccess.add(findById(result));
            }
            catch (EntityNotFoundException e) {}
        }
        
        return listSuccess;
  }
}
