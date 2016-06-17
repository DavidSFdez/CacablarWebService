package uo.sdi.business;

import java.util.List;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.model.Application;
import uo.sdi.model.Seat;

public interface SeatsService {

    // Asientos
    Seat findSeatByUserAndTrip(Long idUser, Long idTrip);

    void cancelSeat(Seat seat) throws EntityNotFoundException;

    List<Seat> findByTrip(Long id);

    // Peticiones de asientos
    void requestSeat(Long idTrip, Long idUser)
	    throws EntityAlreadyExistsException;

    /**
     * Elimina la peticion.
     * 
     * @param idUser
     * @param idTrip
     * @throws EntityNotFoundException
     */
    void removeApplication(Long idUser, Long idTrip)
	    throws EntityNotFoundException;

    /**
     * Pasa la peticion a asiento cancelado.
     * 
     * @param application
     * @throws EntityNotFoundException
     * @throws EntityAlreadyExistsException
     */
    void cancelApplication(Application application)
	    throws EntityNotFoundException, EntityAlreadyExistsException;

    /**
     * Pasa la peticion a asiento aceptado.
     * 
     * @param application
     * @throws EntityNotFoundException
     * @throws EntityAlreadyExistsException
     */
    void acceptApplication(Application application)
	    throws EntityNotFoundException, EntityAlreadyExistsException;

    List<Application> findApplicationByTrip(Long id);

    Application findApplication(Long idUser, Long id);

    // TODO estos son los que parece que no estan en su sitio.
    // Se esta llamando desde:
    // * TripBean.java :: Factories.services.getSeatsService().seatsToUpdate(applications);
    // * ApplicationsAccept.java :: Factories.services.getSeatsService().seatsToUpdate(applications);
    void seatsToUpdate(List<Application> applications)
	    throws EntityAlreadyExistsException;

    // Nunca se usa
    List<Application> getToUpdate();

}
