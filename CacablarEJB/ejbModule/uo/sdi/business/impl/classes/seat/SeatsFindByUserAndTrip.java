package uo.sdi.business.impl.classes.seat;

import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Seat;

public class SeatsFindByUserAndTrip {

    public Seat find(Long idUser, Long idTrip) throws EntityNotFoundException {
	Long[] ids = { idUser, idTrip };
	Seat seat = Factories.persistence.createSeatDao().findById(ids);
	if (seat == null)
	    throw new EntityNotFoundException(
		    "No existe un asiento asociado al usuario " + idUser
			    + " para el viaje " + idTrip);
	return seat;

    }

}
