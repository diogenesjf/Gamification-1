package ch.heigvd.gamification.services.crud.interfaces.remote;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Application;
import java.util.List;

/**
 *
 * @author Gaël Jobin
 */
public interface IApplicationsManagerRemote {

    public long create(Application applicationData);

    public void update(Application newState) throws EntityNotFoundException;

    public void delete(long id) throws EntityNotFoundException;

    public Application findById(long id) throws EntityNotFoundException;

    public List<Application> findAll();
}
