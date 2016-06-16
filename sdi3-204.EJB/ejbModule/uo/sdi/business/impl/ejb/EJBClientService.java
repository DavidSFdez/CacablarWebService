package uo.sdi.business.impl.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

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
import uo.sdi.model.Application;
import uo.sdi.model.Rating;
import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.model.User;

@Stateless
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
    public List<User> listAllUsers() {
	return usersService.findAllUsers();
    }

    @Override
    public User findUserById(Long userId) {
	return usersService.findUserById(userId);
    }

    @Override
    public void cancelUsuario(Long userId) {
	usersService.cancelUser(userId);
    }

    @Override
    public List<Trip> listAllTrips() {
	return tripsService.findAllTrips();
    }

    @Override
    public List<Trip> ListAllTripsPromotedByUser(Long userId) {
	return tripsService.findAllPromoted(userId);
    }

    @Override
    public List<Trip> ListAllTripsWhereUserParticipated(Long userId) {
	return tripsService.findAllParticipated(userId);
    }

    @Override
    public Trip findTripById(Long tripId) {
	return tripsService.findTripById(tripId);
    }


    @Override
    public List<Seat> listAllSeatsFromTrip(Long tripId) {
	return seatsService.findByTrip(tripId);
    }

    @Override
    public Seat findSeatByUserAndTrip(Long userId, Long tripId) {
	return seatsService.findByUserAndTrip(userId, tripId);
    }

    //Arreglar método para que devuelva lista
    @Override
    public List<Rating> listAllRatingsAboutTrip(Long tripId) {
	return ratingsService.listByTrip(tripId);
    }

    @Override
    public void cancelRating(Long ratingId) {
	ratingsService.delete(ratingId);
	
    }

    @Override
    public Rating findRatingById(Long ratingId) {
	return ratingsService.findRatingById(ratingId);
    }

}
