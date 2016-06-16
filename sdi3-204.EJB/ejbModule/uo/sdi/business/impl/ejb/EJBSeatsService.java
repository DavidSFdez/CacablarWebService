package uo.sdi.business.impl.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.business.impl.classes.application.ApplicationsByTrip;
import uo.sdi.business.impl.classes.application.ApplicationsFind;
import uo.sdi.business.impl.classes.seat.SeatsCancelSeat;
import uo.sdi.business.impl.classes.seat.SeatsFindByTrip;
import uo.sdi.business.impl.classes.seat.SeatsFindByUserAndTrip;
import uo.sdi.business.impl.classes.seat.SeatsRequest;
import uo.sdi.business.impl.classes.seat.SeatsToUpdate;
import uo.sdi.business.impl.local.LocalSeatsService;
import uo.sdi.business.impl.remote.RemoteSeatsService;
import uo.sdi.model.Application;
import uo.sdi.model.Seat;

@Stateless
@WebService(name = "SeatsService")
public class EJBSeatsService implements LocalSeatsService, RemoteSeatsService {

    @Override
    public Seat findByUserAndTrip(Long idUser, Long idTrip)
	    throws EntityNotFoundException {
	return new SeatsFindByUserAndTrip().find(idUser, idTrip);
    }

    @Override
    public void request(Long idTrip, Long idUser)
	    throws EntityAlreadyExistsException {
	new SeatsRequest().request(idTrip, idUser);
    }

    @Override
    public void cancelSeat(Seat seat) throws EntityNotFoundException {
	new SeatsCancelSeat().cancel(seat);
    }

    @Override
    public List<Seat> findByTrip(Long idTrip) {
	return new SeatsFindByTrip().find(idTrip);
    }

    @Override
    public Application findApplication(Long idUser, Long id)
	    throws EntityNotFoundException {
	ApplicationsFind action = new ApplicationsFind();
	return action.find(idUser, id);
    }

    @Override
    public List<Application> findApplicationByTrip(Long id) {
	ApplicationsByTrip action = new ApplicationsByTrip();
	return action.find(id);
    }

    @Override
    public void seatsToUpdate(List<Application> applications)
	    throws EntityAlreadyExistsException {
	new SeatsToUpdate().update(applications);

    }

}
