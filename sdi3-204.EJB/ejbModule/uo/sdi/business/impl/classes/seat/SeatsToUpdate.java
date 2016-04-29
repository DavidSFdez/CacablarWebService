package uo.sdi.business.impl.classes.seat;

import java.util.List;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Application;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.exception.AlreadyPersistedException;

public class SeatsToUpdate {

    public void update(List<Application> applications) throws EntityAlreadyExistsException {

	SeatDao sd = Factories.persistence.createSeatDao();

	try {
	    for (Application a : applications) {
		Seat seatToUpdate = new Seat();
		seatToUpdate.setComment("Sin Plaza");
		seatToUpdate.setStatus(SeatStatus.SIN_PLAZA);
		seatToUpdate.setTripId(a.getTripId());
		seatToUpdate.setUserId(a.getUserId());

		if (sd.findByUserAndTrip(seatToUpdate.getUserId(), seatToUpdate.getTripId()) == null) {
		    sd.save(seatToUpdate);
		}
	    }
	} catch (AlreadyPersistedException e) {
	    throw new EntityAlreadyExistsException(
		    "La plaza que se intenta añadir ya existe");
	}

    }

}
