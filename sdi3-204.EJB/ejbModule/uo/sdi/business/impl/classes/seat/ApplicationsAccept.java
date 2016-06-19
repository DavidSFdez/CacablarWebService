package uo.sdi.business.impl.classes.seat;

import java.util.ArrayList;
import java.util.List;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Application;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.persistence.ApplicationDao;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.TripDao;
import uo.sdi.persistence.exception.AlreadyPersistedException;
import uo.sdi.persistence.exception.NotPersistedException;

public class ApplicationsAccept {

    public void execute(Application application)
	    throws EntityNotFoundException, EntityAlreadyExistsException {
	Long[] ids = { application.getUserId(), application.getTripId() };
	// ids[0] = userId
	// ids[1] = tripId

	// El asiento que se va a crear
	Seat seat = new Seat();
	seat.setUserId(ids[0]);
	seat.setTripId(ids[1]);
	seat.setStatus(SeatStatus.ADMITIDO);
	// seat.setComment(comment);

	Trip trip = Factories.persistence.createTripDao().findById(ids[1]);

	// No quedan plazas
	if (trip.getAvailablePax() == 0) {
	    String errorMessage = "No hay plazas libres en el viaje.";
	    throw new BusinessException(errorMessage);
	}

	// Acciones
	TripDao tripDao = Factories.persistence.createTripDao();
	ApplicationDao applicationDao = Factories.persistence
		.createApplicationDao();
	SeatDao seatDao = Factories.persistence.createSeatDao();

	// Reducir en uno las plazas disponibles para tal viaje
	trip.setAvailablePax(trip.getAvailablePax() - 1);

	// Si el número de plazas disponibles es 0, actualiza el estado del
	// viaje a cerrado, y todas las solicitudes pendientes las pone como
	// Seats con el estado SIN_PLAZA
	if (trip.getAvailablePax() == 0) {
	    trip.setStatus(TripStatus.CLOSED);

	    List<Application> applications = applicationDao.findByTripId(trip
		    .getId());

	    for (Application a : applications) {
		Seat seatAux = new Seat();
		seatAux.setStatus(SeatStatus.SIN_PLAZA);
		seatAux.setTripId(a.getTripId());
		seatAux.setUserId(a.getUserId());
		Long[] aux = { a.getUserId(), a.getTripId() };
		try {
		    applicationDao.delete(aux);
		    seatDao.save(seatAux);
		} catch (NotPersistedException e) {
		    // TODO JORGE
		    e.printStackTrace();
		} catch (AlreadyPersistedException e) {
		    // TODO JORGE
		    e.printStackTrace();
		}

	    }

	}

	try {
	    tripDao.update(trip);
	} catch (NotPersistedException e) {
	    throw new EntityNotFoundException("No se enuentra el viaje.", e);
	}

	try {
	    applicationDao.delete(ids);
	} catch (NotPersistedException e) {
	    throw new EntityNotFoundException("No se enuentra la petición..", e);
	}

	try {
	    seatDao.save(seat);
	} catch (AlreadyPersistedException e) {
	    throw new EntityAlreadyExistsException("ya existe el asiento.", e);
	}

    }

}
