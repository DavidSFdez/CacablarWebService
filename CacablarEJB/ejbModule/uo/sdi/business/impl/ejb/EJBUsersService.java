package uo.sdi.business.impl.ejb;

import javax.ejb.Stateless;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.business.impl.classes.user.UserBaja;
import uo.sdi.business.impl.classes.user.UsersAlta;
import uo.sdi.business.impl.local.LocalUsersService;
import uo.sdi.business.impl.remote.RemoteUsersService;
import uo.sdi.model.User;

@Stateless
public class EJBUsersService implements LocalUsersService, RemoteUsersService{

    @Override
    public User findById(Long id) throws EntityNotFoundException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public User saveUser(User user) throws EntityAlreadyExistsException {
	
	return new UsersAlta().save(user);
	
    }

    @Override
    public void updateUser(User user) throws EntityNotFoundException {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void deleteUser(Long id) throws EntityNotFoundException {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void cancelUser(Long id) {
	
	new UserBaja().cancelUser(id);
	
    }
}
