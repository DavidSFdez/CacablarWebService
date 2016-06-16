package uo.sdi.business.impl.classes.seat;

import java.util.List;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Application;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.exception.AlreadyPersistedException;

/**
 * Elimina las peticiones y las añade como asientos sin plaza.
 * 
 */
//TODO nombre mas adecuado
public class SeatsToUpdate {

    public void update(List<Application> applications)
	    throws EntityAlreadyExistsException {

	SeatDao sd = Factories.persistence.createSeatDao();

	for (Application a : applications) {
	    Seat seat = new Seat();
	    seat.setComment("Sin Plaza");
	    seat.setStatus(SeatStatus.SIN_PLAZA);
	    seat.setTripId(a.getTripId());
	    seat.setUserId(a.getUserId());
	    try {
		sd.save(seat);
	    } catch (AlreadyPersistedException e) {
		throw new EntityAlreadyExistsException(
			"La plaza que se intenta añadir ya existe");
	    }
	}

    }

}
