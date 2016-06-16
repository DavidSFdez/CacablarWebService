package uo.sdi.business;

import java.util.List;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.User;

public interface UsersService {

    User findUserById(Long id);

    void saveUser(User user) throws EntityAlreadyExistsException;

    List<User> findAllUsers();

    void updateUser(User user) throws EntityNotFoundException;

    void deleteUser(Long id) throws EntityNotFoundException;

    void cancelUser(Long id) throws EntityNotFoundException;

    List<User> findUsersOnTripByStatus(Long tripId, SeatStatus status);

}
