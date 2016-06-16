package uo.sdi.business.impl.classes.trip;

import uo.sdi.infrastructure.Factories;

public class TripsUpdateTripsStatus {

    public void update() {

	// TODO nombre mas explicativo (actualiza el estado de viajes que han
	// pasado de fecha y que ya no tienen plazas
	// (que ya no tienen plazas ya se hace al aceptar viajes)
	Factories.persistence.createTripDao().updateTripsStatus();

    }

}
