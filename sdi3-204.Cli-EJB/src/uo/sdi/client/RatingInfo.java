package uo.sdi.client;

import java.util.Date;

import uo.sdi.model.Rating;
import uo.sdi.model.Trip;

public class RatingInfo {

    private Rating rating;
    private Trip trip;
    
    public RatingInfo(Rating rating, Trip trip){
	this.rating=rating;
	this.trip=trip;
    }

    public Date getFecha(){
	return trip.getArrivalDate();
    }
    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
    
    
}
