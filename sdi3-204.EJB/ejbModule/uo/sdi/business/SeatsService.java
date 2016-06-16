package uo.sdi.business;

import java.util.List;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.model.Application;
import uo.sdi.model.Seat;

public interface SeatsService {

    // Asientos
    Seat findByUserAndTrip(Long idUser, Long idTrip);

    List<Seat> findByTrip(Long id);

    // Peticiones de asientos
    void request(Long idTrip, Long idUser) throws EntityAlreadyExistsException;

    List<Application> findApplicationByTrip(Long id);

    Application findApplication(Long idUser, Long id);

    // Comun
    void cancelSeat(Seat seat) throws EntityNotFoundException;

    void seatsToUpdate(List<Application> applications)
	    throws EntityAlreadyExistsException;

}
