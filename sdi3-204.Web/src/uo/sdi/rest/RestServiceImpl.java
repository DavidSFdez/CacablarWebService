package uo.sdi.rest;

import static uo.sdi.rest.filter.RestAuthenticationFilter.CURRENT_USER;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import uo.sdi.business.TripsService;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;
import uo.sdi.model.User;

public class RestServiceImpl implements RestService{
    
    TripsService tripsService = Factories.services.getTripsService();
    
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

}
