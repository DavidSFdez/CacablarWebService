package uo.sdi.client;

import java.rmi.Remote;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import uo.sdi.business.RattingsService;
import uo.sdi.business.TripsService;
import uo.sdi.business.UsersService;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.business.impl.RemoteEJBServiceLocator;
import uo.sdi.model.Rating;
import uo.sdi.model.Trip;
import uo.sdi.model.User;

public class Main {

    public static void main(String[] args) throws Exception {
	run();

    }

    private static void run() throws Exception {
	metodocutre();

	System.out.println("###listarUsuariosSistema();");
	listarUsuariosSistema();

	System.out.println("###deshabilitarUsuario();");
	deshabilitarUsuario();

	System.out.println("###listarComentariosYPuntuaciones();");
	listarComentariosYPuntuaciones();
	
	System.out.println("###eliminarRatting();");
	eliminarRatting();
    }


    private static void metodocutre() throws EntityNotFoundException {
	RemoteEJBServiceLocator ejbl = new RemoteEJBServiceLocator();
	
	System.out.println(ejbl.createUsersService());
	System.out.println(ejbl.createTripsService());
	System.out.println(ejbl.createRattingsService());
	System.out.println(ejbl.createSeatsService());
	System.out.println(ejbl.createApplicationService());
	System.out.println(ejbl.createLoginService());
	
	UsersService us = new RemoteEJBServiceLocator().createUsersService();
	
	System.out.println("Usuarios del sistema");
	List<User> users = us.findAll();
	for(User u : users)
	    System.out.println(u);
	
	System.out.println("Viajes del sistema");
	List<Trip> trips = ejbl.createTripsService().findAll();
	for(Trip t : trips)
	    System.out.println(t);
    }

    private static void listarUsuariosSistema() throws Exception {
	User user;

	UsersService us = new RemoteEJBServiceLocator().createUsersService();
	user = us.findById(101L);

	TripsService ts = new RemoteEJBServiceLocator().createTripsService();

	List<Trip> promotedTrips = ts.findAllPromoted(user.getId());
	List<Trip> participateTrips = ts.findAllParticipated(user.getId());

	imprimirDatosUsuarioViajes(user, promotedTrips.size(),
		participateTrips.size());
    }

    private static void imprimirDatosUsuarioViajes(User user,
	    int numberPromoted, int numberParticipated) {
	System.out.println("\nId: " + user.getId() + "\nUsuario: "
		+ user.getLogin());

	System.out.println("-----------------------------");
	System.out.println("\nPromocionados: " + numberPromoted
		+ "\nParticipa: " + numberParticipated);
	System.out.println("-----------------------------");

    }

    private static void deshabilitarUsuario() throws Exception {

	User user;

	UsersService us = new RemoteEJBServiceLocator().createUsersService();
	user = us.findById(101L);

	if (user != null)
	    us.cancelUser(user.getId());
    }

    private static void listarComentariosYPuntuaciones() {
	
	RattingsService rs = new RemoteEJBServiceLocator().createRattingsService();
	
	try {
	    
		List<Trip> trips = new RemoteEJBServiceLocator()
			.createTripsService().findAll();

		List<Trip> tripsLastMonth = new LinkedList<>();
		Date actual = new Date();
		Date unMesAntes = getNewDateMonth(actual,-1);
		
		for(Trip t : trips){
		    if(t.getArrivalDate().after(unMesAntes) && t.getArrivalDate().before(actual)){
			tripsLastMonth.add(t);
		    }
		}

		List<Rating> rattings = new LinkedList<>();
		for(Trip t : tripsLastMonth){
		    rattings.add(rs.listByTrip(t.getId()));
		}
		
		imprimeRattings(rattings);
	    
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    private static Date getNewDateMonth(Date actual, int months) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(actual);
	    cal.add(Calendar.MONTH, months); 
	    return cal.getTime();
    }

    private static void imprimeRattings(List<Rating> rattings) {
	// TODO Auto-generated method stub
	
    }
    
    private static void eliminarRatting() {
	RattingsService rs = new RemoteEJBServiceLocator().createRattingsService();
	
	Rating r = new Rating();
	
	rs.delete(r.getId());
	
    }

}
