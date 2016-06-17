package uo.sdi.business;

import java.util.List;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.model.Application;

/**
 * 
 * @deprecated AppliactionsService es parte del servicio de asientos. Usar {@link uo.sdi.business.SeatsService} 
 * @see {@link uo.sdi.business.SeatsService} 
 */
@Deprecated
public interface ApplicationService {

    /**
     * 
     * @deprecated Usar {@link uo.sdi.business.SeatsService.findApplication} 
     * @see {@link uo.sdi.business.SeatsService} 
     */
    Application findApplication(Long idTrip, Long idUser);

    /**
     * 
     * @deprecated Usar {@link uo.sdi.business.SeatsService.removeApplication} 
     * @see {@link uo.sdi.business.SeatsService} 
     */
    void remove(Long idUser, Long idTrip) throws EntityNotFoundException;

    /**
     * TODO esto puede que tampoco esté aqui luego
     * @deprecated Usar {@link uo.sdi.business.SeatsService} 
     * @see {@link uo.sdi.business.SeatsService} 
     */
    List<Application> getToUpdate();

    /**
     * 
     * @deprecated Usar {@link uo.sdi.business.SeatsService.acceptApplication} 
     * @see {@link uo.sdi.business.SeatsService} 
     */
    void acceptApplication(Application application)
	    throws EntityNotFoundException, EntityAlreadyExistsException;

    /**
     * 
     * @deprecated Usar {@link uo.sdi.business.SeatsService.cancelApplication} 
     * @see {@link uo.sdi.business.SeatsService} 
     */
    void cancelApplication(Application application)
	    throws EntityNotFoundException, EntityAlreadyExistsException;

}
