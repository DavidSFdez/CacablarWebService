package uo.sdi.business.impl.classes.user;

import java.util.List;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Application;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.model.User;
import uo.sdi.model.UserStatus;
import uo.sdi.persistence.ApplicationDao;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.TripDao;
import uo.sdi.persistence.UserDao;
import uo.sdi.persistence.exception.NotPersistedException;

public class UsersBaja {

    public void cancelUser(Long id) throws EntityNotFoundException {
	UserDao dao = Factories.persistence.createUserDao();
	TripDao td = Factories.persistence.createTripDao();
	SeatDao sd = Factories.persistence.createSeatDao();
	ApplicationDao ad = Factories.persistence.createApplicationDao();

	User user = dao.findById(id);
//TODO JORGE revisa todas las excepciones, no tiene que devolverlas si el viaje no existe
	//y el mensaje que he puesto para cada una
	List<Trip> trips = td.findAllParticipated(id);

	// pongo como excluido todos los viajes en los que va a
	// participar
	for (Trip t : trips) {
	    Seat s = sd.findByUserAndTrip(user.getId(), t.getId());
	    Long[] ids = {user.getId(),t.getId()};
	    
	    s.setStatus(SeatStatus.EXCLUIDO);
	    try {
		sd.update(s);
	    } catch (NotPersistedException e) {
		throw new EntityNotFoundException("No exists el viaje.", e);
	    }
	   
	    try {
		ad.delete(ids);
	    } catch (NotPersistedException e) {
		throw new BusinessException(e.getMessage());
	    }
	}

	trips = td.findAllPromoted(id);

	// pongo como cancelados todos los viajes que promociona
	for (Trip t : trips) {
	    t.setStatus(TripStatus.CANCELLED);
	    
	    
	    //Pongo como excluidos a todas las personas que participaban en el viaje
	    List<Seat> seats = sd.findByTrip(t.getId());
	    for(Seat s : seats){
		s.setStatus(SeatStatus.EXCLUIDO);
		try {
		    sd.update(s);
		} catch (NotPersistedException e) {
		   
		   throw new BusinessException(e.getMessage());
		}
	    }
	    //Borro todas las solicitudes pendientes a ese viaje
	    List<Application> applications = ad.findByTripId(t.getId());
	    for(Application a : applications){
		Long[] ids = {a.getUserId(),a.getTripId()};
		try {
		    ad.delete(ids);
		} catch (NotPersistedException e) {
		   
		    throw new BusinessException(e.getMessage());
		}
	    }
	    
	    try {
		td.update(t);
	    } catch (NotPersistedException e) {
		throw new EntityNotFoundException("No exists el viaje.", e);
	    }
	}

	// pongo al usuario como cancelado
	user.setStatus(UserStatus.CANCELLED);
	try {
	    dao.update(user);
	} catch (NotPersistedException e) {
	    throw new EntityNotFoundException("No exists el usuario.", e);
	}

    }
}
