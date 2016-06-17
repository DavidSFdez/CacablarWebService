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
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.business.impl.local.LocalClientService;
import uo.sdi.business.impl.local.LocalRattingsService;
import uo.sdi.business.impl.local.LocalSeatsService;
import uo.sdi.business.impl.local.LocalTripsService;
import uo.sdi.business.impl.local.LocalUsersService;
import uo.sdi.business.impl.remote.RemoteClientService;
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
    public List<UserInfo> listUsersInfo() {
	List<User> users = usersService.findAllUsers();
	List<UserInfo> usersInfo = new ArrayList<>();
	UserInfo ui = null;

	for (User u : users) {
	    ui = new UserInfo();
	    List<Trip> promotedTrips = tripsService.findAllPromoted(u.getId());
	    List<Trip> participatedTrips = tripsService.findAllParticipated(u
		    .getId());
	    ui.setUser(u);
	    ui.setNumPromoted(promotedTrips.size());
	    ui.setNumParticipated(participatedTrips.size());
	    usersInfo.add(ui);
	}
	return usersInfo;
    }

    @Override
    public void disableUser(Long userId) {
	try {
	    usersService.cancelUser(userId);
	} catch (EntityNotFoundException e) {
	   System.out.println(e.getMessage());
	}

    }
//TODO DAVID : Crear el hacedor de DTOs (no tengo ganas de hacerlo ahora)
    @Override
    public List<RatingInfo> listRatings(int numMonths) {
	
	List<Trip> allTrips = tripsService.findAllTrips();
	Date actual = new Date();
	Date ant = getNewDateMonth(actual, numMonths);
	List<Trip> trips = new ArrayList<>();
	
	for (Trip t : allTrips)
	    if (t.getArrivalDate().after(ant)
		    && t.getArrivalDate().before(actual))
		trips.add(t);
	List<Rating> ratings = null;
	List<RatingInfo> ri = new ArrayList<>();

	for (Trip t : trips) {
	    ratings = ratingsService.listByTrip(t.getId());
	    for (Rating r : ratings){
		RatingInfo rat = new RatingInfo();
		rat.setRating(r);
		rat.setDestino(t.getDestination().getCity());
		rat.setArrivalDate(t.getArrivalDate());
		ri.add(rat);
	    }
	}

	Coollection cool = new Coollection();

	ri = cool.from(ri).orderBy("fecha", Order.DESC).all();

	return ri;
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

    
    //TODO JORGE el try/catch
    @Override
    public void removeRating(Long ratingId) {
	try {
	    ratingsService.delete(ratingId);
	} catch (EntityNotFoundException e) {
	   System.out.println(e.getMessage());
	}
    }

}
