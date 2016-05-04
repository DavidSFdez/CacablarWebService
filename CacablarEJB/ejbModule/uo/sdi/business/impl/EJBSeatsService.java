package uo.sdi.business.impl;

import java.util.List;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.model.Application;
import uo.sdi.model.Seat;
import uo.sdi.model.User;

public class EJBSeatsService implements LocalSeatsService,RemoteSeatsService{

    @Override
    public User verify(String name, String password) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Seat findByUserAndTrip(Long idUser, Long idTrip)
	    throws EntityNotFoundException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Seat> findByTrip(Long id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void request(Long idTrip, Long idUser)
	    throws EntityAlreadyExistsException {
	// TODO Auto-generated method stub
	
    }

    @Override
    public List<Application> findApplicationByTrip(Long id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Application findApplication(Long idUser, Long id)
	    throws EntityNotFoundException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void cancelSeat(Seat seat) throws EntityNotFoundException {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void seatsToUpdate(List<Application> applications)
	    throws EntityAlreadyExistsException {
	// TODO Auto-generated method stub
	
    }

}
