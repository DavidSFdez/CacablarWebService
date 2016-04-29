package uo.sdi.business.impl;

import javax.ejb.Local;

import uo.sdi.business.UsersService;

@Local
public interface LocalUsersService extends UsersService{
    
    static final String USERS_SERVICE_JNDI_KEY =
	    "java:global/"
	    + "sdi3-204/"
	    + "sdi3-204.EJB"
	    + "EjbUsersService!" + "com.sdi.business.impl.RemoteUsersService";

}
