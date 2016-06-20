package uo.sdi.business.impl.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.business.impl.classes.maintenance.ApplicationsYSeatsActualizarAutomaticamente;
import uo.sdi.business.impl.classes.seat.ApplicationCancel;
import uo.sdi.business.impl.classes.seat.ApplicationsAccept;
import uo.sdi.business.impl.classes.seat.ApplicationsByTrip;
import uo.sdi.business.impl.classes.seat.ApplicationsFind;
import uo.sdi.business.impl.classes.seat.ApplicationsRemove;
import uo.sdi.business.impl.classes.seat.SeatsCancelSeat;
import uo.sdi.business.impl.classes.seat.SeatsFindByTrip;
import uo.sdi.business.impl.classes.seat.SeatsFindByUserAndTrip;
import uo.sdi.business.impl.classes.seat.SeatsRequest;
import uo.sdi.business.impl.local.LocalSeatsService;
import uo.sdi.business.impl.remote.RemoteSeatsService;
import uo.sdi.model.Application;
import uo.sdi.model.Seat;

@Stateless
@WebService(name = "SeatsService")
public class EJBSeatsService implements LocalSeatsService, RemoteSeatsService {

    @Override
    public Seat findSeatByUserAndTrip(Long idUser, Long idTrip) {
	return new SeatsFindByUserAndTrip().find(idUser, idTrip);
    }

    @Override
    public void requestSeat(Long idTrip, Long idUser)
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
    public List<Application> findApplicationByTrip(Long id) {
	ApplicationsByTrip action = new ApplicationsByTrip();
	return action.find(id);
    }

    @Override
    public Application findApplication(Long idTrip, Long idUser) {
	return new ApplicationsFind().find(idTrip, idUser);
    }

    @Override
    public void removeApplication(Long idUser, Long idTrip) {
	new ApplicationsRemove().remove(idUser, idTrip);

    }

    @Override
    public void acceptApplication(Application application)
	    throws EntityNotFoundException, EntityAlreadyExistsException {
	new ApplicationsAccept().execute(application);
    }

    @Override
    public void cancelApplication(Application application){
	new ApplicationCancel().execute(application);
    }


    @Override
    public void actualizarAsientosAutomaticamente() {
	new ApplicationsYSeatsActualizarAutomaticamente().actualizar();
	
    }


}
