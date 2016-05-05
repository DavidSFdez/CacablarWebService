package uo.sdi.business;



import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.model.User;


public interface UsersService {

    	User findById(Long id) throws EntityNotFoundException;
	User saveUser(User user) throws EntityAlreadyExistsException;
	void updateUser(User user) throws EntityNotFoundException;
	void deleteUser(Long id) throws EntityNotFoundException;
	void cancelUser(Long id);
}
