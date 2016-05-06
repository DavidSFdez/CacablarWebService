package uo.sdi.business.impl.classes.user;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.User;
import uo.sdi.persistence.UserDao;

public class UsersFindById {

    public User find(Long id) {
	UserDao ud = Factories.persistence.createUserDao();
	return ud.findById(id);
    }

}
