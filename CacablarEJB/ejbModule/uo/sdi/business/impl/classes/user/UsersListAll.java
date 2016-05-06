package uo.sdi.business.impl.classes.user;

import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.persistence.UserDao;

public class UsersListAll {
    
    public List<uo.sdi.model.User> listAll(){
	UserDao dao = Factories.persistence.createUserDao();
	
	List<uo.sdi.model.User> users = dao.findAll();
	
	return users;
    }

}
