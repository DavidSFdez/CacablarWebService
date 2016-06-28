package uo.sdi.rest;

import static uo.sdi.rest.filter.RestAuthenticationFilter.CURRENT_USER;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import uo.sdi.business.SeatsService;
import uo.sdi.business.TripsService;
import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Application;
import uo.sdi.model.Trip;
import uo.sdi.model.User;

public class RestServiceImpl implements RestService {

    TripsService tripsService = Factories.services.getTripsService();
    SeatsService seatsService = Factories.services.getSeatsService();

    @Override
    public List<Trip> tripsAll(@Context HttpServletRequest request) {
	User user = (User) request.getAttribute(CURRENT_USER);
	System.out.println("id: " + user.getId());
	return tripsService.findAllTrips();
    }

    @Override
    public List<Trip> tripsPromoted(@Context HttpServletRequest request) {
	User user = (User) request.getAttribute(CURRENT_USER);
	System.out.println("id: " + user.getId());
	return tripsService.findAllPromotedAndActive(user.getId());
    }

    @Override
    public List<uo.sdi.model.Application> getApplicationsByTrip(
	    HttpServletRequest request, Long idTrip) {
	User user = (User) request.getAttribute(CURRENT_USER);
	Trip trip = tripsService.findTripById(idTrip);

	if (!trip.getPromoterId().equals(user.getId())) {
	    return new LinkedList<>();
	}

	return seatsService.findApplicationByTrip(idTrip);
    }

    @Override
    public void aceptApplications(Application application)
	    throws EntityAlreadyExistsException {
	seatsService.acceptApplication(application);
    }

}
