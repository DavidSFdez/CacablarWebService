package uo.sdi.business.impl.classes.trip;

import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;
import uo.sdi.persistence.TripDao;

public class TripsFindAll {

    public List<Trip> find() {

	TripDao td = Factories.persistence.createTripDao();

	return td.findAll();
    }

}
