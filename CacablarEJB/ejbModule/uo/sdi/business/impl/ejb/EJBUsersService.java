package uo.sdi.business.impl.ejb;

import java.util.List;

import javax.ejb.Stateless;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.business.impl.classes.user.UsersAlta;
import uo.sdi.business.impl.classes.user.UsersBaja;
import uo.sdi.business.impl.classes.user.UsersFindById;
import uo.sdi.business.impl.classes.user.UsersListAll;
import uo.sdi.business.impl.classes.user.UsersUpdate;
import uo.sdi.business.impl.local.LocalUsersService;
import uo.sdi.business.impl.remote.RemoteUsersService;
import uo.sdi.model.User;

@Stateless
public class EJBUsersService implements LocalUsersService, RemoteUsersService {

    @Override
    public User findById(Long id) throws EntityNotFoundException {
	return new UsersFindById().find(id);
    }

    @Override
    public User saveUser(User user) throws EntityAlreadyExistsException {
	return new UsersAlta().save(user);
    }

    @Override
    public void updateUser(User user) throws EntityNotFoundException {
	new UsersUpdate().update(user);
    }

    @Override
    public void deleteUser(Long id) throws EntityNotFoundException {
	new UsersBaja().cancelUser(id);
    }

    @Override
    public void cancelUser(Long id) {
	new UsersBaja().cancelUser(id);
    }

    @Override
    public List<User> findAll() {
	return new UsersListAll().listAll();
    }
}
