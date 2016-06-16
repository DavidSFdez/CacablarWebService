package uo.sdi.business.impl.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.business.impl.classes.user.UsersActiveOnTrip;
import uo.sdi.business.impl.classes.user.UsersAlta;
import uo.sdi.business.impl.classes.user.UsersBaja;
import uo.sdi.business.impl.classes.user.UsersFindById;
import uo.sdi.business.impl.classes.user.UsersListAll;
import uo.sdi.business.impl.classes.user.UsersUpdate;
import uo.sdi.business.impl.local.LocalUsersService;
import uo.sdi.business.impl.remote.RemoteUsersService;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.User;

@Stateless
@WebService(name = "UsersService")
public class EJBUsersService implements LocalUsersService, RemoteUsersService {

    @Override
    public User findUserById(Long id) {
	return new UsersFindById().find(id);
    }

    @Override
    public void saveUser(User user) throws EntityAlreadyExistsException {
	new UsersAlta().save(user);
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
    public void cancelUser(Long id) throws EntityNotFoundException {
	new UsersBaja().cancelUser(id);
    }

    @Override
    public List<User> findAllUsers() {
	return new UsersListAll().listAll();
    }

    @Override
    public List<User> findUsersOnTripByStatus(Long tripId, SeatStatus status) {
	return new UsersActiveOnTrip().list(tripId, status);
    }
}
