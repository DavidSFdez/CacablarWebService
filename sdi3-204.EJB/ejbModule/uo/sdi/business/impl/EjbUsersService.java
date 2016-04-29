package uo.sdi.business.impl;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.model.User;


public class EjbUsersService implements LocalUsersService, RemoteUsersService{

    private static final String USERS_SERVICE_JNDI_KEY =
	    "java:global/"
	    + "sdi3-204/"
	    + "sdi3-204.EJB"
	    + "EjbUsersService!" + "com.sdi.business.impl.RemoteUsersService";
    
    @Override
    public User findById(Long id) throws EntityNotFoundException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public User saveUser(User user) throws EntityAlreadyExistsException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void updateUser(User user) throws EntityNotFoundException {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void deleteUser(Long id) throws EntityNotFoundException {
	// TODO Auto-generated method stub
	
    }

}
