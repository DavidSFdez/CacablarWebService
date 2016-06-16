package uo.sdi.business;

import java.util.List;

import uo.sdi.model.Rating;
import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.model.User;

public interface ClientService {

   
    List<User> listAllUsers();
    User findUserById(Long userId);
    void cancelUsuario(Long userId);
    
    List<Trip> listAllTrips();
    List<Trip> ListAllTripsPromotedByUser(Long userId);
    List<Trip> ListAllTripsWhereUserParticipated(Long userId);
    Trip findTripById(Long tripId);
    
    List<Seat> listAllSeatsFromTrip(Long tripId);
    Seat findSeatByUserAndTrip(Long userId,Long tripId);
    
    List<Rating> listAllRatingsAboutTrip(Long tripId);
    Rating findRatingById(Long ratingId);
    void cancelRating(Long idRating);
}
