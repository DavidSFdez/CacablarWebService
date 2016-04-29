package uo.sdi.business.impl.classes.application;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Application;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.persistence.ApplicationDao;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.Transaction;
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
	Transaction transaction = Factories.persistence.createTransaction();

	transaction.begin();
	try {
	    applicationDao.delete(ids);
	    seatDao.save(seat);
	    transaction.commit();
	} catch (NotPersistedException e) {
	    transaction.rollback();
	    throw new EntityNotFoundException(e);
	} catch (AlreadyPersistedException e) {
	    transaction.rollback();
	    throw new EntityAlreadyExistsException(e);
	}

    }

}
