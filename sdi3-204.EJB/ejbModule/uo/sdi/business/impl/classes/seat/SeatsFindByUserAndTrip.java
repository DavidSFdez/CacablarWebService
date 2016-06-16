package uo.sdi.business.impl.classes.seat;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Seat;

public class SeatsFindByUserAndTrip {

    public Seat find(Long idUser, Long idTrip) {
	Long[] ids = { idUser, idTrip };
	return Factories.persistence.createSeatDao().findById(ids);
    }

}
