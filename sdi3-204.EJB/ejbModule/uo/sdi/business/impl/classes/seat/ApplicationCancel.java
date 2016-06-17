package uo.sdi.business.impl.classes.seat;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Application;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.persistence.ApplicationDao;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.exception.AlreadyPersistedException;
import uo.sdi.persistence.exception.NotPersistedException;

public class ApplicationCancel {

    public void execute(Application application)
	    throws EntityNotFoundException, EntityAlreadyExistsException {
	Long[] ids = { application.getUserId(), application.getTripId() };
	// ids[0] = userId
	// ids[1] = tripId

	// El asiento que se va a crear
	Seat seat = new Seat();
	seat.setUserId(ids[0]);
	seat.setTripId(ids[1]);
	seat.setStatus(SeatStatus.EXCLUIDO);
	// seat.setComment(comment);

	ApplicationDao applicationDao = Factories.persistence
		.createApplicationDao();
	SeatDao seatDao = Factories.persistence.createSeatDao();

	try {
	    applicationDao.delete(ids);
	} catch (NotPersistedException e) {
	    throw new EntityNotFoundException(
		    "No existe la peticion que quiere borrar.", e);
	}

	try {
	    seatDao.save(seat);
	} catch (AlreadyPersistedException e) {
	    throw new EntityAlreadyExistsException(
		    "No existe la peticion que quiere borrar.", e);
	}

    }

}
