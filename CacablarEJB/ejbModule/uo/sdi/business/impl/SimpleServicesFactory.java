package uo.sdi.business.impl;


import uo.sdi.business.ApplicationService;
import uo.sdi.business.LoginService;
import uo.sdi.business.RattingsService;
import uo.sdi.business.SeatsService;
import uo.sdi.business.ServicesFactory;
import uo.sdi.business.TripsService;
import uo.sdi.business.UsersService;

public class SimpleServicesFactory implements ServicesFactory {

	@Override
	public UsersService createUsersService() {
		
	    return new SimpleUsersService();
	}

	@Override
	public LoginService createLoginService() {
	    
	    return new SimpleLoginService();
	}

	@Override
	public TripsService createTripsService() {
	    
	    return new SimpleTripsService();
	}

	@Override
	public SeatsService createSeatsService() {
	    
	    return new SimpleSeatsService();
	}

	@Override
	public ApplicationService createApplicationService() {
	    
	    return new SimpleApplicationsService();
	}

	@Override
	public RattingsService createRattingsService() {
	    // TODO Auto-generated method stub
	    return null;
	}

	

}
