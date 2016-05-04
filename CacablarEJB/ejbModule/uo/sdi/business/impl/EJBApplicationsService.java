package uo.sdi.business.impl;

import java.util.List;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.model.Application;

public class EJBApplicationsService implements LocalApplicationsService,RemoteApplicationsService {

    @Override
    public Application find(Long idTrip, Long idUser)
	    throws EntityNotFoundException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void remove(Long idUser, Long idTrip) throws EntityNotFoundException {
	// TODO Auto-generated method stub
	
    }

    @Override
    public List<Application> getToUpdate() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void acceptApplication(Application application)
	    throws EntityAlreadyExistsException, EntityNotFoundException {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void cancelApplication(Application application)
	    throws EntityAlreadyExistsException, EntityNotFoundException {
	// TODO Auto-generated method stub
	
    }

}
