package uo.sdi.business;

public interface ServicesFactory {

    UsersService getUsersService();

    LoginService getLoginService();

    TripsService getTripsService();

    SeatsService getSeatsService();

    ApplicationService getApplicationService();

    RattingsService getRattingsService();

    ClientService getClientService();

}
