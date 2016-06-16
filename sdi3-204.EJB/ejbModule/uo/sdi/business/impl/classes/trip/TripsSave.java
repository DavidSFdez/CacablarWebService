package uo.sdi.business.impl.classes.trip;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.persistence.exception.AlreadyPersistedException;

public class TripsSave {

    public void save(Trip trip, Long idUser)
	    throws EntityAlreadyExistsException {
	//TODO estas tareas que hay a continuacion:
	// Comprobar fechas
	// Numero de asientos válido (los disponibles < totales)
	// Que el coste no sea negativo
	// etc

	trip.setPromoterId(idUser);
	trip.setStatus(TripStatus.OPEN);

	try {
	    Factories.persistence.createTripDao().save(trip);
	} catch (AlreadyPersistedException e) {
	    throw new EntityAlreadyExistsException("Viaje ya existe " + trip, e);
	}

    }

}
