package uo.sdi.business.impl.classes.user;

import java.util.List;

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

    public void cancelUser(Long id) {
	UserDao dao = Factories.persistence.createUserDao();
	TripDao td = Factories.persistence.createTripDao();
	SeatDao sd = Factories.persistence.createSeatDao();

	User user = dao.findById(id);
	try {
	    if (user != null) {

		List<Trip> trips = td.findAllParticipated(id);

		// pongo como excluido todos los viajes en los que va a
		// participar
		for (Trip t : trips) {
		    Seat s = sd.findByUserAndTrip(user.getId(), t.getId());
		    s.setStatus(SeatStatus.EXCLUIDO);
		    sd.update(s);
		}

		trips = td.findAllPromoted(id);

		// pongo como cancelados todos los viajes que promociona
		for (Trip t : trips) {
		    t.setStatus(TripStatus.CANCELLED);
		    td.update(t);
		}

		// pongo al usuario como cancelado
		user.setStatus(UserStatus.CANCELLED);
		dao.update(user);

	    }
	} catch (NotPersistedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }
}
