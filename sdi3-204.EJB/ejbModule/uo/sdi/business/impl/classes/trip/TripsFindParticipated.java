package uo.sdi.business.impl.classes.trip;

import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;

public class TripsFindParticipated {

    public List<Trip> find(Long id) {
	return Factories.persistence.createTripDao().findAllParticipated(id);
    }

}
