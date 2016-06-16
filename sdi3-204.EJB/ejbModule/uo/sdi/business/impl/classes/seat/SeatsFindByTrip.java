package uo.sdi.business.impl.classes.seat;

import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Seat;

public class SeatsFindByTrip {

    public List<Seat> find(Long idTrip) {

	return Factories.persistence.createSeatDao().findByTrip(idTrip);
    }

}
