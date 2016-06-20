package uo.sdi.business.impl.classes.maintenance;

import java.util.List;

import alb.util.log.Log;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Application;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.persistence.ApplicationDao;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.exception.AlreadyPersistedException;

/**
 * 1)Busca todas las Applications que pertenecen a viajes que NO se encuentran
 * en estado 0 (OPEN)
 * 
 * 2) LAS BORRA
 * 
 * La razón de hacer esto es que el enunciado dice que cuando se cierre un viaje
 * las solicitudes pendientes han de ponerse en seats con el estado 4
 * (SIN_PLAZA)
 * 
 * 3) Guarda estas applications como SEATS con el estado 4 (SIN_PLAZA)
 * 
 * 
 * 
 */
public class ApplicationsYSeatsActualizarAutomaticamente {

    public void actualizar() {
	ApplicationDao ad = Factories.persistence.createApplicationDao();

	List<Application> applications = ad.findToUpdate();

	// borro las applications
	ad.deleteToUpdate();

	SeatDao sd = Factories.persistence.createSeatDao();

	for (Application a : applications) {
	    Seat seat = new Seat();
	    seat.setComment("Sin Plaza");
	    seat.setStatus(SeatStatus.SIN_PLAZA);
	    seat.setTripId(a.getTripId());
	    seat.setUserId(a.getUserId());
	    try {
		sd.save(seat);
	    } catch (AlreadyPersistedException e) {
		Log.warn("Ya existe el asinento [trip:" + seat.getTripId()
			+ ", user" + seat.getUserId() + "].");
	    }

	}

    }

}
