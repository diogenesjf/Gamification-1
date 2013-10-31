package ch.heigvd.gamification.services.crud;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.AppUser;
import ch.heigvd.gamification.model.Success;
import ch.heigvd.gamification.services.crud.interfaces.ISuccessManager;
import java.util.List;

/**
 *
 * @author Alexandre Perusset
 */
public class SuccessManager implements ISuccessManager {

  @Override
  public long create(Success successData) { //TODO
    //ne pas oublier, APRES le em.persist, d'ajouter manuellement le succès à la
    //liste des succès de l'utilisateur
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void update(Success newState) throws EntityNotFoundException { //TODO
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void delete(long id) throws EntityNotFoundException { //TODO
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Success findById(long id) throws EntityNotFoundException { //TODO
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public List<Success> findAll() { //TODO
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public List<AppUser> findAllUsers() { //TODO
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
}
