package uo.sdi.business.impl.classes.user;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.User;
import uo.sdi.persistence.UserDao;
import uo.sdi.persistence.exception.NotPersistedException;

public class UsersUpdate {

    public void update(User user) {
	UserDao ud = Factories.persistence.createUserDao();

	try {
	    ud.update(user);
	} catch (NotPersistedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

}
