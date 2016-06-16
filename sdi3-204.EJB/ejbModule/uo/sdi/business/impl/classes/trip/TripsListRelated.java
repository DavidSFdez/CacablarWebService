package uo.sdi.business.impl.classes.trip;

import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;

public class TripsListRelated {

    public List<Trip> list(Long idUser) throws Exception {

	List<Trip> trips = null;

	trips = Factories.persistence.createTripDao().findWhenParticipated(
		idUser);

	return trips;

    }
}
