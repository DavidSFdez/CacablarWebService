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

public class RemoteEJBServiceLocator implements ServicesFactory {

    private static final String USERS_SERVICE_JNDI_KEY = "sdi3-204/"
	    + "sdi3-204.EJB/" + "EJBUsersService!"
	    + "uo.sdi.business.impl.remote.RemoteUsersService";

    private static final String TRIPS_SERVICE_JNDI_KEY = "sdi3-204/"
	    + "sdi3-204.EJB/" + "EJBTripsService!"
	    + "uo.sdi.business.impl.remote.RemoteTripsService";

    private static final String RATTINGS_SERVICE_JNDI_KEY = "sdi3-204/"
	    + "sdi3-204.EJB/" + "EJBRattingsService!"
	    + "uo.sdi.business.impl.remote.RemoteRattingsService";

    private static final String LOGGIN_SERVICE_JNDI_KEY = "sdi3-204/"
	    + "sdi3-204.EJB/" + "EJBLoginService!"
	    + "uo.sdi.business.impl.remote.RemoteLoginService";

    private static final String SEATS_SERVICE_JNDI_KEY = "sdi3-204/"
	    + "sdi3-204.EJB/" + "EJBSeatsService!"
	    + "uo.sdi.business.impl.remote.RemoteSeatsService";

    private static final String APPLICATIONS_SERVICE_JNDI_KEY = "sdi3-204/"
	    + "sdi3-204.EJB/" + "EJBApplicationsService!"
	    + "uo.sdi.business.impl.remote.RemoteApplicationsService";

    private static final String CLIENT_SERVICE_JNDI_KEY = "sdi3-204/"
	    + "sdi3-204.EJB/" + "EJBClientService!"
	    + "uo.sdi.business.impl.remote.RemoteClientService";

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
	    TripsService ts = (TripsService) ctx.lookup(TRIPS_SERVICE_JNDI_KEY);
	    return (ts);
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
	try {
	    Context ctx = new InitialContext();
	    return (ClientService) ctx.lookup(CLIENT_SERVICE_JNDI_KEY);
	} catch (NamingException e) {
	    throw new RuntimeException("JNDI problem", e);
	}
    }

}
