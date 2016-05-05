package uo.sdi.business.impl.classes.user;

import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.model.User;
import uo.sdi.model.UserStatus;
import uo.sdi.persistence.TripDao;
import uo.sdi.persistence.UserDao;

public class UserBaja {

    public void cancelUser(Long id) {
	UserDao dao = Factories.persistence.createUserDao();
	TripDao td = Factories.persistence.createTripDao();
	
	User user = dao.findById(id);
	
	if(user!=null){
	    
	    user.setStatus(UserStatus.CANCELLED);
	    
	    List<Trip> trips = td.findWhenParticipated(id);
	    
	    //TODO revisar si los viajes que devuelve están activos aún
	    //Todo HACERLO :)
	    
	}
	
    }

}
