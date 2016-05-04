package uo.sdi.business.impl;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.model.User;


public class EJBUsersService implements LocalUsersService, RemoteUsersService{

    @Override
    public User findById(Long id) throws EntityNotFoundException {
	// TODO Auto-generated method stub
	return new User();
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
