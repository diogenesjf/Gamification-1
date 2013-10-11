package ch.heigvd.gamification.services.to;

import ch.heigvd.gamification.model.Employee;
import ch.heigvd.gamification.to.PublicEmployeeTO;
import javax.ejb.Stateless;

/**
 *
 * @author Olivier Liechti
 */
@Stateless
public class EmployeesTOService implements EmployeesTOServiceLocal {

  public PublicEmployeeTO buildPublicEmployeeTO(Employee source) {
    PublicEmployeeTO to = new PublicEmployeeTO(source.getId(), source.getFirstName(), source.getLastName(), source.getEmail());
    return to;
  }

}
