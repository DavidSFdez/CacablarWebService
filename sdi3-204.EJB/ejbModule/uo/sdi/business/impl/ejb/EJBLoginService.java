package uo.sdi.business.impl.ejb;

import javax.ejb.Stateless;
import javax.jws.WebService;

import uo.sdi.business.impl.local.LocalLoginService;
import uo.sdi.business.impl.remote.RemoteLoginService;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.User;
import uo.sdi.persistence.UserDao;

@Stateless
@WebService(name = "LoginService")
public class EJBLoginService implements LocalLoginService, RemoteLoginService {

    @Override
    public User verify(String login, String password) {
	UserDao userDao = Factories.persistence.createUserDao();
	User user = userDao.validateLogin(login, password);
	return user;
    }
}
