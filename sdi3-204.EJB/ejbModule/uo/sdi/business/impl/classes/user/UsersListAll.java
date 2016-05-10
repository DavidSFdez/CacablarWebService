package uo.sdi.business.impl.classes.user;

import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.User;
import uo.sdi.persistence.UserDao;

public class UsersListAll {
    
    public List<User> listAll(){
	UserDao dao = Factories.persistence.createUserDao();
	
	List<User> users = dao.findAll();
	
	return users;
    }

}
