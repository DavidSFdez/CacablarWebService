package uo.sdi.business.impl.ejb;

import java.util.List;

import javax.ejb.Stateless;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.business.impl.classes.trip.TripsCancel;
import uo.sdi.business.impl.classes.trip.TripsFindById;
import uo.sdi.business.impl.classes.trip.TripsFindByIdAndPromoter;
import uo.sdi.business.impl.classes.trip.TripsListActive;
import uo.sdi.business.impl.classes.trip.TripsListActiveToUser;
import uo.sdi.business.impl.classes.trip.TripsListRelated;
import uo.sdi.business.impl.classes.trip.TripsSave;
import uo.sdi.business.impl.classes.trip.TripsUpdate;
import uo.sdi.business.impl.classes.trip.TripsUpdateTripsStatus;
import uo.sdi.business.impl.local.LocalTripsService;
import uo.sdi.business.impl.remote.RemoteTripsService;
import uo.sdi.model.Trip;
@Stateless
public class EJBTripsService implements LocalTripsService, RemoteTripsService{

    @Override
    public List<Trip> listActive() {
	return new TripsListActive().list();
    }

    @Override
    public List<Trip> listRelated(Long idUser) throws Exception {
	
	return new TripsListRelated().list(idUser);
    }

    @Override
    public List<Trip> listActiveToUser(Long idUser) throws Exception {
	
	return new TripsListActiveToUser().list(idUser);
    }
    
    @Override
    public void update(Trip trip,Long idUser) throws EntityNotFoundException{

	new TripsUpdate().update(trip,idUser);
	
    }

    @Override
    public void cancel(Trip trip,Long idUser) throws EntityNotFoundException, EntityAlreadyExistsException {
	new TripsCancel().cancel(trip,idUser);
	
    }

    @Override
    public void save(Trip trip,Long idUser) throws EntityAlreadyExistsException {
	new TripsSave().save(trip,idUser);
	
    }

    @Override
    public Trip findByIdandPromoter(Long idTrip, Long idUser){
	
	return new TripsFindByIdAndPromoter().find(idTrip,idUser);
    }

    @Override
    public Trip findById(Long idTrip) throws EntityNotFoundException {
	
	return new TripsFindById().find(idTrip);
    }

    @Override
    public void updateTripsStatus() {
	new TripsUpdateTripsStatus().update();
	
    }

  

}
