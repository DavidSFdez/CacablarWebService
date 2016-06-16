package uo.sdi.business.impl.classes.seat;

import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Seat;

public class SeatsFindByUserAndTrip {

    public Seat find(Long idUser, Long idTrip) throws EntityNotFoundException {
	Long[] ids = { idUser, idTrip };
	return Factories.persistence.createSeatDao().findById(ids);
    }

}
