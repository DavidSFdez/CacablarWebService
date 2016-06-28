package uo.sdi.business.impl.classes.trip;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.persistence.exception.AlreadyPersistedException;

public class TripsSave {

    public void save(Trip trip, Long idUser)
	    throws EntityAlreadyExistsException {

	if (trip.getArrivalDate().before(trip.getDepartureDate()))
	    throw new BusinessException(
		    "La fecha de llegada no puede ser anterior a la de "
			    + "salida");
	else if (trip.getClosingDate().after(trip.getDepartureDate()))
	    throw new BusinessException(
		    "La fecha límite para solicitar plaza no puede ser "
			    + "después de la de salida");
	else if (trip.getAvailablePax() > trip.getMaxPax()
		|| trip.getAvailablePax() < 0)
	    throw new BusinessException(
		    "El número de plazas totales no puede ser menor que "
			    + "el de plazas disponibles o menor que 0");
	else if (trip.getEstimatedCost() < 0)
	    throw new BusinessException("El coste estimado no puede ser "
		    + "negativo");

	trip.setPromoterId(idUser);
	trip.setStatus(TripStatus.OPEN);

	try {
	    Factories.persistence.createTripDao().save(trip);
	} catch (AlreadyPersistedException e) {
	    throw new EntityAlreadyExistsException("Viaje ya existe " + trip, e);
	}

    }

}
