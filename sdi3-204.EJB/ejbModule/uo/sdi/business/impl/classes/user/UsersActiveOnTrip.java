package uo.sdi.business.impl.classes.user;

import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.User;
import uo.sdi.persistence.UserDao;

public class UsersActiveOnTrip {

    public List<User> list(Long tripId, SeatStatus status) {
	UserDao dao = Factories.persistence.createUserDao();

	List<User> users = dao.findUsersOnTripByStatus(tripId, status);

	return users;
    }

}
