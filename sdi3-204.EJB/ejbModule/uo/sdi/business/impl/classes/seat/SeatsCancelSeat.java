package uo.sdi.business.impl.classes.seat;

import java.util.Date;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.TripDao;
import uo.sdi.persistence.exception.NotPersistedException;

public class SeatsCancelSeat {

    public void cancel(Seat seatAux) throws EntityNotFoundException {

	// Daos
	SeatDao sd = Factories.persistence.createSeatDao();
	TripDao td = Factories.persistence.createTripDao();

	// Ids
	Long[] ids = { seatAux.getUserId(), seatAux.getTripId() };

	// Existe el viaje
	Trip trip = td.findById(seatAux.getTripId());
	if (trip == null)
	    throw new EntityNotFoundException(
		    "Se ha producido un error: No existe el viaje asociado a la plaza");

	// Existe el asiento
	Seat seat = sd.findById(ids);

	if (null == seat)
	    throw new EntityNotFoundException(
		    "No existe el sitio que se intenta cancelar");

	if (trip.getAvailablePax() == 0
		&& trip.getClosingDate().after(new Date())) {
	    trip.setAvailablePax(trip.getAvailablePax() + 1);
	    trip.setStatus(TripStatus.OPEN);
	    seat.setStatus(SeatStatus.EXCLUIDO);

	} else if (trip.getClosingDate().after(new Date())) {
	    trip.setAvailablePax(trip.getAvailablePax() + 1);
	    seat.setStatus(SeatStatus.EXCLUIDO);

	} else
	    throw new BusinessException("El viaje ya pasó");

	try {
	    sd.update(seat);
	} catch (NotPersistedException e) {
	    throw new EntityNotFoundException("No existe el asiento.", e);
	}
	try {
	    td.update(trip);
	} catch (NotPersistedException e) {
	    throw new EntityNotFoundException("No existe el viaje.", e);
	}

    }

}
