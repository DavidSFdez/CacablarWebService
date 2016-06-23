package uo.sdi.business.impl.ejb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import uo.sdi.business.RattingsService;
import uo.sdi.business.SeatsService;
import uo.sdi.business.TripsService;
import uo.sdi.business.UsersService;
import uo.sdi.business.impl.local.LocalClientService;
import uo.sdi.business.impl.local.LocalRattingsService;
import uo.sdi.business.impl.local.LocalSeatsService;
import uo.sdi.business.impl.local.LocalTripsService;
import uo.sdi.business.impl.local.LocalUsersService;
import uo.sdi.business.impl.remote.RemoteClientService;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Rating;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.model.DTO.RatingInfo;
import uo.sdi.model.DTO.UserInfo;

import com.wagnerandade.coollection.Coollection;
import com.wagnerandade.coollection.query.order.Order;

@Stateless
@WebService(name = "ClientService")
public class EJBClientService implements LocalClientService,
	RemoteClientService {

    @EJB(beanInterface = LocalUsersService.class)
    private UsersService usersService;

    @EJB(beanInterface = LocalTripsService.class)
    private TripsService tripsService;

    @EJB(beanInterface = LocalSeatsService.class)
    private SeatsService seatsService;

    @EJB(beanInterface = LocalRattingsService.class)
    private RattingsService ratingsService;

    @Override
    public List<User> getUsers(){
	return usersService.findAllUsers();
    }
    
    @Override
    public List<UserInfo> listUsersInfo() {
	List<User> users = Factories.services.getUsersService().findAllUsers();
	List<UserInfo> usersInfo = new ArrayList<>();
	UserInfo ui = null;

	for (User u : users) {
	    ui = new UserInfo();
	    List<Trip> promotedTrips = Factories.services.getTripsService()
		    .findAllPromoted(u.getId());
	    List<Trip> participatedTrips = Factories.services.getTripsService()
		    .findAllParticipated(u.getId());
	    ui.setUser(u);
	    ui.setNumPromoted(promotedTrips.size());
	    ui.setNumParticipated(participatedTrips.size());
	    usersInfo.add(ui);
	}
	return usersInfo;
    }

    @Override
    public void disableUser(Long userId) {
	usersService.cancelUser(userId);

    }

    @Override
    public List<RatingInfo> listRatings() {
	
	return ratingsService.findRatingsLastMonth();
    }

    // Le pasas una fecha y el tiempo y te devuelve esa fecha
    // Por ejemplo, fecha actual y -1 mes, te devuelve un date con la fecha
    // Del mes pasado
    private Date getNewDateMonth(Date actual, int months) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(actual);
	cal.add(Calendar.MONTH, months);
	return cal.getTime();
    }

    @Override
    public void removeRating(Long ratingId) {
	ratingsService.delete(ratingId);

    }

}
