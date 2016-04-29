package uo.sdi.business.impl;

import uo.sdi.business.LoginService;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.User;
import uo.sdi.persistence.UserDao;

public class SimpleLoginService implements LoginService {

    @Override
    public User verify(String login, String password) {
	UserDao userDao = Factories.persistence.createUserDao();
	User user = userDao.validateLogin(login, password);
	return user;
    }
}
