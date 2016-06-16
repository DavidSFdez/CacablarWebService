package uo.sdi.business.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import uo.sdi.business.ApplicationService;
import uo.sdi.business.ClientService;
import uo.sdi.business.LoginService;
import uo.sdi.business.RattingsService;
import uo.sdi.business.SeatsService;
import uo.sdi.business.ServicesFactory;
import uo.sdi.business.TripsService;
import uo.sdi.business.UsersService;

public class LocalEJBServiceLocator implements ServicesFactory {

    private static final String USERS_SERVICE_JNDI_KEY = "java:global/"
	    + "sdi3-204/" + "sdi3-204.EJB/" + "EJBUsersService!"
	    + "uo.sdi.business.impl.local.LocalUsersService";

    private static final String TRIPS_SERVICE_JNDI_KEY = "java:global/"
	    + "sdi3-204/" + "sdi3-204.EJB/" + "EJBTripsService!"
	    + "uo.sdi.business.impl.local.LocalTripsService";

    private static final String RATTINGS_SERVICE_JNDI_KEY = "java:global/"
	    + "sdi3-204/" + "sdi3-204.EJB/" + "EJBRattingsService!"
	    + "uo.sdi.business.impl.local.LocalRattingsService";

    private static final String LOGGIN_SERVICE_JNDI_KEY = "java:global/"
	    + "sdi3-204/" + "sdi3-204.EJB/" + "EJBLoginService!"
	    + "uo.sdi.business.impl.local.LocalLoginService";

    private static final String SEATS_SERVICE_JNDI_KEY = "java:global/"
	    + "sdi3-204/" + "sdi3-204.EJB/" + "EJBSeatsService!"
	    + "uo.sdi.business.impl.local.LocalSeatsService";

    private static final String APPLICATIONS_SERVICE_JNDI_KEY = "java:global/"
	    + "sdi3-204/" + "sdi3-204.EJB/" + "EJBApplicationsService!"
	    + "uo.sdi.business.impl.local.LocalApplicationsService";

    @Override
    public UsersService getUsersService() {
	try {
	    Context ctx = new InitialContext();
	    return (UsersService) ctx.lookup(USERS_SERVICE_JNDI_KEY);
	} catch (NamingException e) {
	    throw new RuntimeException("JNDI problem", e);
	}
    }

    @Override
    public LoginService getLoginService() {
	try {
	    Context ctx = new InitialContext();
	    return (LoginService) ctx.lookup(LOGGIN_SERVICE_JNDI_KEY);
	} catch (NamingException e) {
	    throw new RuntimeException("JNDI problem", e);
	}
    }

    @Override
    public TripsService getTripsService() {
	try {
	    Context ctx = new InitialContext();
	    return (TripsService) ctx.lookup(TRIPS_SERVICE_JNDI_KEY);
	} catch (NamingException e) {
	    throw new RuntimeException("JNDI problem", e);
	}
    }

    @Override
    public SeatsService getSeatsService() {
	try {
	    Context ctx = new InitialContext();
	    return (SeatsService) ctx.lookup(SEATS_SERVICE_JNDI_KEY);
	} catch (NamingException e) {
	    throw new RuntimeException("JNDI problem", e);
	}
    }

    @Override
    public ApplicationService getApplicationService() {
	try {
	    Context ctx = new InitialContext();
	    return (ApplicationService) ctx
		    .lookup(APPLICATIONS_SERVICE_JNDI_KEY);
	} catch (NamingException e) {
	    throw new RuntimeException("JNDI problem", e);
	}
    }

    @Override
    public RattingsService getRattingsService() {
	try {
	    Context ctx = new InitialContext();
	    return (RattingsService) ctx.lookup(RATTINGS_SERVICE_JNDI_KEY);
	} catch (NamingException e) {
	    throw new RuntimeException("JNDI problem", e);
	}
    }

    @Override
    public ClientService getClientService() {
	// TODO Auto-generated method stub
	return null;
    }

}
