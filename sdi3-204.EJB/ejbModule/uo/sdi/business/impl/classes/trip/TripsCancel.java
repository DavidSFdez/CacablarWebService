package uo.sdi.business.impl.classes.trip;

import java.util.Date;
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
import uo.sdi.persistence.exception.AlreadyPersistedException;
import uo.sdi.persistence.exception.NotPersistedException;

public class TripsCancel {

    public void cancel(Trip trip, Long idUser) throws EntityNotFoundException,
	    EntityAlreadyExistsException {

	ApplicationDao ad = Factories.persistence.createApplicationDao();
	SeatDao sd = Factories.persistence.createSeatDao();

	if (trip.getClosingDate().after(new Date()))
	    throw new BusinessException("El viaje ha pasado.");
	if (trip.getPromoterId() == idUser)
	    throw new BusinessException(
		    "El usuario que cancela el viaje no es su promotor.");

	List<Application> applications = ad.findByTripId(trip.getId());
	List<Seat> seats = sd.findByTrip(trip.getId());

	// pongo el estado a cancelado
	trip.setStatus(TripStatus.CANCELLED);
	// paso a sin plaza los aceptados
	for (Seat s : seats) {
	    if (s.getStatus().equals(SeatStatus.ADMITIDO)) {
		s.setStatus(SeatStatus.SIN_PLAZA);
		try {
		    sd.update(s);
		} catch (NotPersistedException e) {
		    throw new EntityNotFoundException("No existe el asiento.",
			    e);
		}
	    }
	}
	// paso a sin plaza los pendientes
	for (Application a : applications) {
	    Seat s = new Seat();
	    s.setComment("Viaje cancelado");
	    s.setStatus(SeatStatus.SIN_PLAZA);
	    s.setTripId(trip.getId());
	    s.setUserId(a.getUserId());
	    try {
		sd.save(s);
	    } catch (AlreadyPersistedException e) {
		throw new EntityAlreadyExistsException("Ya existe el asiento.",
			e);
	    }
	    // borro las application
	    Long[] ids = { a.getUserId(), a.getTripId() };
	    try {
		ad.delete(ids);
	    } catch (NotPersistedException e) {
		throw new EntityNotFoundException("No existe la petición.", e);
	    }
	}

	try {
	    Factories.persistence.createTripDao().update(trip);
	} catch (NotPersistedException e) {
	    throw new EntityNotFoundException("No existe en viaje.", e);
	}

    }

}
