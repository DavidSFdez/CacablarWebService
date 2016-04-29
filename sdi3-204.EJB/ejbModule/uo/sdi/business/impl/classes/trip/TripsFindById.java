package uo.sdi.business.impl.classes.trip;

import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;

public class TripsFindById {

    public Trip find(Long idTrip) throws EntityNotFoundException {

	Trip trip = Factories.persistence.createTripDao().findById(idTrip);
	
	if (trip == null)
	    throw new EntityNotFoundException("No existe viaje "+idTrip);

	return trip;

    }

}
