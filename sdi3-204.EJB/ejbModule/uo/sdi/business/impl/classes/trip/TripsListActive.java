package uo.sdi.business.impl.classes.trip;

import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;

public class TripsListActive {

    public List<Trip> list() {

	List<Trip> trips = null;

	trips = Factories.persistence.createTripDao().findAllActive();

	return trips;

    }
}
