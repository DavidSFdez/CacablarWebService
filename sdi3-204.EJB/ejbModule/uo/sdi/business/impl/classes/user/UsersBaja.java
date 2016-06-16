package uo.sdi.business.impl.classes.user;

import java.util.List;

import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.model.User;
import uo.sdi.model.UserStatus;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.TripDao;
import uo.sdi.persistence.UserDao;
import uo.sdi.persistence.exception.NotPersistedException;

public class UsersBaja {

    public void cancelUser(Long id) throws EntityNotFoundException {
	UserDao dao = Factories.persistence.createUserDao();
	TripDao td = Factories.persistence.createTripDao();
	SeatDao sd = Factories.persistence.createSeatDao();

	User user = dao.findById(id);

	List<Trip> trips = td.findAllParticipated(id);

	// pongo como excluido todos los viajes en los que va a
	// participar
	for (Trip t : trips) {
	    Seat s = sd.findByUserAndTrip(user.getId(), t.getId());
	    s.setStatus(SeatStatus.EXCLUIDO);
	    try {
		sd.update(s);
	    } catch (NotPersistedException e) {
		throw new EntityNotFoundException("No exists el viaje.", e);
	    }
	}

	trips = td.findAllPromoted(id);

	// pongo como cancelados todos los viajes que promociona
	for (Trip t : trips) {
	    t.setStatus(TripStatus.CANCELLED);
	    try {
		td.update(t);
	    } catch (NotPersistedException e) {
		throw new EntityNotFoundException("No exists el viaje.", e);
	    }
	}

	// pongo al usuario como cancelado
	// TODO debemos tmb cancelar los asientos y peticiones de los viajes que
	// promociona?
	user.setStatus(UserStatus.CANCELLED);
	try {
	    dao.update(user);
	} catch (NotPersistedException e) {
	    throw new EntityNotFoundException("No exists el usuario.", e);
	}

    }
}
