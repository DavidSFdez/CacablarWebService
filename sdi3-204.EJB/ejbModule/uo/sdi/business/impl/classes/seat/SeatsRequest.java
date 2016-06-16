package uo.sdi.business.impl.classes.seat;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Seat;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.exception.AlreadyPersistedException;

public class SeatsRequest {

    public void request(Long idTrip, Long idUser)
	    throws EntityAlreadyExistsException {

	SeatDao sd = Factories.persistence.createSeatDao();

	if (sd.findByUserAndTrip(idUser, idTrip) != null)
	    throw new EntityAlreadyExistsException();

	Seat seat = new Seat();
	seat.setTripId(idTrip);
	seat.setUserId(idUser);

	try {
	    sd.save(seat);
	} catch (AlreadyPersistedException e) {
	    throw new EntityAlreadyExistsException("ya existe el viaje.", e);
	}

    }

}
