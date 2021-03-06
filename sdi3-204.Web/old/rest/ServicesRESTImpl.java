package uo.sdi.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import uo.sdi.business.SeatsService;
import uo.sdi.business.TripsService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;
import uo.sdi.model.User;

public class ServicesRESTImpl implements ServiceREST {

    private TripsService tripsService;
    private SeatsService seatsService;
    {
	tripsService = Factories.services.getTripsService();
	seatsService = Factories.services.getSeatsService();
    }

    @Override
    public boolean login(String usuario, String password,
	    @Context HttpServletRequest req) {

	User user = null;
	user = Factories.services.getLoginService()
		.verify(usuario, password);
	if (user != null)
	    req.getSession().setAttribute("userId", user.getId());
	return user == null;
    }

    @Override
    public List<Trip> listPromotedActiveTrips(@Context HttpServletRequest req) {

	Object id = req.getSession().getAttribute("userId");
	List<Trip> trips = null;

	if (id != null) {
	    Long idUser = (Long) id;
	    trips = tripsService.findAllPromotedAndActive(idUser);
	} else
	    throw new BusinessException(
		    "No se encuentra ningún usuario logueado");

	return trips;
    }

    @Override
    public List<uo.sdi.model.Application> selectTrip(Long idTrip) {
	return seatsService.findApplicationByTrip(idTrip);
    }

    @Override
    public void acceptSeat(uo.sdi.model.Application application) {
	try {
	    seatsService.acceptApplication(application);
	} catch (EntityAlreadyExistsException e) {
	  
	    e.printStackTrace();
	} catch (EntityNotFoundException e) {
	  
	    e.printStackTrace();
	}

    }

    @Override
    public Trip[] testREST() {
	return Factories.services.getTripsService().findAllTrips()
		.toArray(new Trip[0]);
    }

}
