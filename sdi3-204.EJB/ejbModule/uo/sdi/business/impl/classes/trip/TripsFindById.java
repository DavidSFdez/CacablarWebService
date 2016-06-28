package uo.sdi.business.impl.classes.trip;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;

public class TripsFindById {

    public Trip find(Long idTrip) {

	Trip trip = Factories.persistence.createTripDao().findById(idTrip);

	return trip;

    }

}
