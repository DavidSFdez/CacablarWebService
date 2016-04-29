package uo.sdi.business.impl.classes.trip;

import uo.sdi.infrastructure.Factories;

public class TripsUpdateTripsStatus {

    public void update() {

	Factories.persistence.createTripDao().updateTripsStatus();
	
    }

}
