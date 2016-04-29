package uo.sdi.business.impl;

import java.util.List;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.model.Trip;

public class EJBTripsService implements LocalTripsService, RemoteTripsService{

    @Override
    public List<Trip> listActive() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Trip> listRelated(Long idUser) throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Trip> listActiveToUser(Long idUser) throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void update(Trip trip, Long idUser) throws EntityNotFoundException {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void cancel(Trip trip, Long idUser) throws EntityNotFoundException,
	    EntityAlreadyExistsException {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void save(Trip trip, Long idUser)
	    throws EntityAlreadyExistsException {
	// TODO Auto-generated method stub
	
    }

    @Override
    public Trip findByIdandPromoter(Long idTrip, Long idUser) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Trip findById(Long idTrip) throws EntityNotFoundException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void updateTripsStatus() {
	// TODO Auto-generated method stub
	
    }

}
