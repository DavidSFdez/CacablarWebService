package uo.sdi.business.impl.classes.trip;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;

public class TripsFindByIdAndPromoter {

    public Trip find(Long idTrip, Long idUser) {
	
	return Factories.persistence.createTripDao().findByIdAndPromoter(idTrip,idUser);
    }

}
