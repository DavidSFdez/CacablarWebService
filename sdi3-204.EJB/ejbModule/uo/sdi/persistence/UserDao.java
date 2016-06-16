package uo.sdi.persistence;

import java.util.List;

import uo.sdi.model.SeatStatus;
import uo.sdi.model.User;
import uo.sdi.persistence.util.GenericDao;

public interface UserDao extends GenericDao<User, Long> {

    User findByLogin(String login);

    User validateLogin(String login, String pass);

    List<User> findUsersOnTripByStatus(Long tripId, SeatStatus status);

}
