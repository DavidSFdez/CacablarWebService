package uo.sdi.business.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import uo.sdi.business.ApplicationService;
import uo.sdi.business.LoginService;
import uo.sdi.business.RattingsService;
import uo.sdi.business.SeatsService;
import uo.sdi.business.ServicesFactory;
import uo.sdi.business.TripsService;
import uo.sdi.business.UsersService;

public class LocalEJBServiceLocator implements ServicesFactory{
    
    private static final String USERS_SERVICE_JNDI_KEY =
	    "java:global/"
	    + "sdi3-204/"
	    + "sdi3-204.EJB"
	    + "EjbUsersService!" + "com.sdi.business.impl.LocalUsersService";
    
    private static final String TRIPS_SERVICE_JNDI_KEY =
	    "java:global/"
	    + "sdi3-204/"
	    + "sdi3-204.EJB"
	    + "EjbTripsService!" + "com.sdi.business.impl.LocalTripsService";
    
    private static final String RATTINGS_SERVICE_JNDI_KEY =
	    "java:global/"
	    + "sdi3-204/"
	    + "sdi3-204.EJB"
	    + "EjbRattingsService!" + "com.sdi.business.impl.LocalRattingsService";
    
    private static final String LOGGIN_SERVICE_JNDI_KEY =
	    "java:global/"
	    + "sdi3-204/"
	    + "sdi3-204.EJB"
	    + "EjbLoginService!" + "com.sdi.business.impl.LocalLoginService";
    
    private static final String SEATS_SERVICE_JNDI_KEY =
	    "java:global/"
	    + "sdi3-204/"
	    + "sdi3-204.EJB"
	    + "EjbSeatsService!" + "com.sdi.business.impl.LocalSeatsService";
    
    private static final String APPLICATIONS_SERVICE_JNDI_KEY =
	    "java:global/"
	    + "sdi3-204/"
	    + "sdi3-204.EJB"
	    + "EjbApplicationsService!" + "com.sdi.business.impl.LocalApplicationsService";
    

    @Override
    public UsersService createUsersService() {
	try {
		Context ctx = new InitialContext();
		return (UsersService) ctx.lookup(USERS_SERVICE_JNDI_KEY);
	} catch (NamingException e) {
		throw new RuntimeException("JNDI problem", e);
	}
    }

    @Override
    public LoginService createLoginService() {
	try {
		Context ctx = new InitialContext();
		return (LoginService) ctx.lookup(LOGGIN_SERVICE_JNDI_KEY);
	} catch (NamingException e) {
		throw new RuntimeException("JNDI problem", e);
	}
    }

    @Override
    public TripsService createTripsService() {
	try {
		Context ctx = new InitialContext();
		return (TripsService) ctx.lookup(TRIPS_SERVICE_JNDI_KEY);
	} catch (NamingException e) {
		throw new RuntimeException("JNDI problem", e);
	}
    }

    @Override
    public SeatsService createSeatsService() {
	try {
		Context ctx = new InitialContext();
		return (SeatsService) ctx.lookup(SEATS_SERVICE_JNDI_KEY);
	} catch (NamingException e) {
		throw new RuntimeException("JNDI problem", e);
	}
    }

    @Override
    public ApplicationService createApplicationService() {
	try {
		Context ctx = new InitialContext();
		return (ApplicationService) ctx.lookup(APPLICATIONS_SERVICE_JNDI_KEY);
	} catch (NamingException e) {
		throw new RuntimeException("JNDI problem", e);
	}
    }

    @Override
    public RattingsService createRattingsService() {
	try {
		Context ctx = new InitialContext();
		return (RattingsService) ctx.lookup(RATTINGS_SERVICE_JNDI_KEY);
	} catch (NamingException e) {
		throw new RuntimeException("JNDI problem", e);
	}
    }

   
    
    

}
