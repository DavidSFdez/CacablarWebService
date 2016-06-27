package uo.sdi.business.impl.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.business.impl.classes.trip.TripsCancel;
import uo.sdi.business.impl.classes.trip.TripsFindAll;
import uo.sdi.business.impl.classes.trip.TripsFindAllPromotedAndActive;
import uo.sdi.business.impl.classes.trip.TripsFindById;
import uo.sdi.business.impl.classes.trip.TripsFindByIdAndPromoter;
import uo.sdi.business.impl.classes.trip.TripsFindParticipated;
import uo.sdi.business.impl.classes.trip.TripsFindPromoted;
import uo.sdi.business.impl.classes.trip.TripsListActive;
import uo.sdi.business.impl.classes.trip.TripsListActiveToUser;
import uo.sdi.business.impl.classes.trip.TripsListRelated;
import uo.sdi.business.impl.classes.trip.TripsSave;
import uo.sdi.business.impl.classes.trip.TripsUpdate;
import uo.sdi.business.impl.classes.trip.TripsUpdateStatusToClose;
import uo.sdi.business.impl.local.LocalTripsService;
import uo.sdi.business.impl.remote.RemoteTripsService;
import uo.sdi.model.Trip;

@Stateless
@WebService(name = "TripsService")
public class EJBTripsService implements LocalTripsService, RemoteTripsService {

    @Override
    public List<Trip> listActive() {
	return new TripsListActive().list();
    }

    @Override
    public List<Trip> listRelated(Long idUser) {
	return new TripsListRelated().list(idUser);
    }

    @Override
    public List<Trip> listActiveToUser(Long idUser) {
	return new TripsListActiveToUser().list(idUser);
    }

    @Override
    public void update(Trip trip, Long idUser) throws EntityNotFoundException {
	new TripsUpdate().update(trip, idUser);
    }

    @Override
    public void cancel(Trip trip, Long idUser) throws EntityNotFoundException,
	    EntityAlreadyExistsException {
	new TripsCancel().cancel(trip, idUser);
    }

    @Override
    public void save(Trip trip, Long idUser)
	    throws EntityAlreadyExistsException {
	new TripsSave().save(trip, idUser);

    }

    @Override
    public Trip findByIdandPromoter(Long idTrip, Long idUser) {
	return new TripsFindByIdAndPromoter().find(idTrip, idUser);
    }

    @Override
    public Trip findTripById(Long idTrip){
	return new TripsFindById().find(idTrip);
    }

    /**
     * Todos los viajes que se hayan acabado sus plazas disponibles
     * o que haya pasado la fecha de cierre
     * cambia su estado a 1 (CLOSED) 
     */
    @Override
    public void updateTripsStatus() {
	new TripsUpdateStatusToClose().update();

    }

    @Override
    public List<Trip> findAllPromoted(Long id) {
	return new TripsFindPromoted().find(id);
    }

    @Override
    public List<Trip> findAllParticipated(Long id) {
	return new TripsFindParticipated().find(id);
    }

    @Override
    public List<Trip> findAllTrips() {
	return new TripsFindAll().find();
    }

    @Override
    public List<Trip> findAllPromotedAndActive(long idUser) {
	return new TripsFindAllPromotedAndActive().find(idUser);
    }

}
