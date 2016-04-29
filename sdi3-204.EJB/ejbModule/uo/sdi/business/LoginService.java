package uo.sdi.business;

import uo.sdi.model.User;

public interface LoginService {

    User verify(String name, String password);

}
