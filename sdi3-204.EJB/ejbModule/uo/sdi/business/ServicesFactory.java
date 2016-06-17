package uo.sdi.business;

public interface ServicesFactory {

    UsersService getUsersService();

    LoginService getLoginService();

    TripsService getTripsService();

    SeatsService getSeatsService();

    /**
     * @deprecated Usar {@link uo.sdi.business.ServicesFactory.getSeatsService}.
     * @see {@link uo.sdi.business.SeatsService}
     * @return
     */
    @Deprecated
    ApplicationService getApplicationService();

    RattingsService getRattingsService();

    ClientService getClientService();

}
