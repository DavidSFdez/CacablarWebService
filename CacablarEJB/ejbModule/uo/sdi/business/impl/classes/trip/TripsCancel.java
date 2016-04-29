package uo.sdi.business.impl.classes.trip;

import java.util.Date;
import java.util.List;

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
import uo.sdi.persistence.Transaction;
import uo.sdi.persistence.exception.AlreadyPersistedException;
import uo.sdi.persistence.exception.NotPersistedException;

public class TripsCancel {

    public void cancel(Trip trip, Long idUser) throws EntityNotFoundException,
	    EntityAlreadyExistsException {
	// TODO Comprobar todas las condiciones para que se pueda cancelar,
	// fechas, que sea promotor blabalbla
	// TODO poner a excluidos a toda la peña que tenga asientos solicitados
	Transaction transaction = Factories.persistence.createTransaction();
	ApplicationDao ad = Factories.persistence.createApplicationDao();
	SeatDao sd = Factories.persistence.createSeatDao();

	transaction.begin();
	try {
	    if (trip.getClosingDate().after(new Date())
		    && trip.getPromoterId() == idUser) {

		List<Application> applications = ad.findByTripId(trip.getId());
		List<Seat> seats = sd.findByTrip(trip.getId());

		// pongo el estado a cancelado
		trip.setStatus(TripStatus.CANCELLED);
		// paso a sin plaza los aceptados
		for (Seat s : seats) {
		    if (s.getStatus().equals(SeatStatus.ADMITIDO)) {
			s.setStatus(SeatStatus.SIN_PLAZA);
			sd.update(s);
		    }
		}
		// paso a sin plaza los pendientes
		for (Application a : applications) {
		    Seat s = new Seat();
		    s.setComment("Viaje cancelado");
		    s.setStatus(SeatStatus.SIN_PLAZA);
		    s.setTripId(trip.getId());
		    s.setUserId(a.getUserId());
		    sd.save(s);
		    //borro las application
		    Long[] ids = { a.getUserId(), a.getTripId() };
		    ad.delete(ids);
		}

		Factories.persistence.createTripDao().update(trip);
		transaction.commit();

	    }
	    Factories.persistence.createTripDao().update(trip);
	} catch (NotPersistedException ex) {
	    transaction.rollback();
	    throw new EntityNotFoundException("Viaje no eliminado "
		    + trip.getId(), ex);

	} catch (AlreadyPersistedException e) {
	    transaction.rollback();
	    throw new EntityAlreadyExistsException("El asiento ya existe");
	}
    }

}
