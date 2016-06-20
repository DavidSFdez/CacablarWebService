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
    void removeApplication(Long idUser, Long idTrip);

    /**
     * Pasa la peticion a asiento cancelado.
     * 
     * @param application
     * @throws EntityNotFoundException
     * @throws EntityAlreadyExistsException
     */
    void cancelApplication(Application application);

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

    void actualizarAsientosAutomaticamente();

}
