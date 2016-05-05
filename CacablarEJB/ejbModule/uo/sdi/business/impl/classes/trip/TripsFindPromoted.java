package uo.sdi.business.impl.classes.trip;

import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;

public class TripsFindPromoted {

    public List<Trip> find(Long id) {
	
	return Factories.services.createTripsService().findAllPromoted(id);
    }

}
