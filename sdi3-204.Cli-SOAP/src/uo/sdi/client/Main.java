package uo.sdi.client;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.sdi.ws.EJBRattingsServiceService;
import com.sdi.ws.EJBTripsServiceService;
import com.sdi.ws.EJBUsersServiceService;
import com.sdi.ws.Rating;
import com.sdi.ws.RattingsService;
import com.sdi.ws.Trip;
import com.sdi.ws.TripsService;
import com.sdi.ws.User;
import com.sdi.ws.UsersService;

public class Main {

    public static void main(String[] args) throws Exception {
	run();

    }

    private static void run() throws Exception {
	// metodocutre();

	System.out.println("###listarUsuariosSistema();");
	listarUsuariosSistema();

	System.out.println("###deshabilitarUsuario();");
	deshabilitarUsuario();

	System.out.println("###listarComentariosYPuntuaciones();");
	listarComentariosYPuntuaciones();

	System.out.println("###eliminarRatting();");
	eliminarRatting();
    }

    private static void metodocutre() {

	UsersService us = new EJBUsersServiceService().getUsersServicePort();
	;

	System.out.println("Usuarios del sistema");
	List<User> users = us.findAll();
	for (User u : users)
	    System.out.println(u);

	System.out.println("Viajes del sistema");
	List<Trip> trips = new EJBTripsServiceService().getTripsServicePort()
		.findAll();
	for (Trip t : trips)
	    System.out.println(t);
    }

    private static void listarUsuariosSistema() throws Exception {
	UsersService us = new EJBUsersServiceService().getUsersServicePort();
	TripsService ts = new EJBTripsServiceService().getTripsServicePort();
	List<User> users = us.findAll();
	System.out.println("-----------------------------");
	for (User u : users) {
	    List<Trip> promotedTrips = ts.findAllPromoted(u.getId());
	    List<Trip> participateTrips = ts.findAllParticipated(u.getId());
	    imprimirDatosUsuarioViajes(u, promotedTrips.size(),
		    participateTrips.size());
	    System.out.println("-------");
	}
    }

    private static void imprimirDatosUsuarioViajes(User user,
	    int numberPromoted, int numberParticipated) {

	System.out.println("\nId: " + user.getId() + "\nUsuario: "
		+ user.getLogin());

	System.out.println("\nPromocionados: " + numberPromoted
		+ "\nParticipa: " + numberParticipated);

    }

    private static void deshabilitarUsuario() throws Exception {

	User user;

	UsersService us = new EJBUsersServiceService().getUsersServicePort();
	user = us.findById(101L);

	if (user != null)
	    us.cancelUser(user.getId());
    }

    private static void listarComentariosYPuntuaciones() {

	RattingsService rs = new EJBRattingsServiceService()
		.getRattingsServicePort();
	TripsService ts = new EJBTripsServiceService().getTripsServicePort();
	try {

	    List<Trip> trips = ts.findAll();

	    List<Trip> tripsLastMonth = new LinkedList<>();
	    Date actual = new Date();
	    Date unMesAntes = getNewDateMonth(actual, -1);

	    for (Trip t : trips) {
		if (t.getArrivalDate().toGregorianCalendar().getTime()
			.after(unMesAntes)
			&& t.getArrivalDate().toGregorianCalendar().getTime()
				.before(actual)) {
		    tripsLastMonth.add(t);
		}
	    }

	    List<Rating> rattings = new LinkedList<>();
	    for (Trip t : tripsLastMonth) {
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
	RattingsService rs = new EJBRattingsServiceService()
		.getRattingsServicePort();

	Rating r = new Rating();

	rs.delete(r.getId());

    }

}
