package uo.sdi.business.impl.classes.trip;

import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;

public class TripsFindAllPromotedAndActive {

    public List<Trip> find(long idUser) {

	return Factories.persistence.createTripDao().findAllPromotedAndActive(
		idUser);
    }

}
