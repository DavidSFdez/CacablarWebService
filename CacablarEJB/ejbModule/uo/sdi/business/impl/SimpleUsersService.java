package uo.sdi.business.impl;

import uo.sdi.business.UsersService;
import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.business.impl.classes.user.UsersAlta;
import uo.sdi.model.User;


/**
 * Clase de implementación (una de las posibles) del interfaz de la fachada de
 * servicios
 * 
 * @author Enrique
 * 
 */
public class SimpleUsersService implements UsersService {


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

	
}
