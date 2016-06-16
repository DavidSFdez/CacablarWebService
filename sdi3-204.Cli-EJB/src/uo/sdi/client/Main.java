package uo.sdi.client;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.wagnerandade.coollection.Coollection;
import com.wagnerandade.coollection.query.order.Order;

import uo.sdi.business.ClientService;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.business.impl.RemoteEJBServiceLocator;
import uo.sdi.model.Rating;
import uo.sdi.model.Trip;
import uo.sdi.model.User;

public class Main {

    ClientService cs = new RemoteEJBServiceLocator().getClientService();
    Scanner in = null;
    public static void main(String[] args) throws Exception {
	new Main().run();

    }

    private void run() throws Exception {
	// metodocutre();
	in = new Scanner(System.in);
	
	System.out.println("###listarUsuariosSistema();");
	listarUsuariosSistema();

	System.out.println("###deshabilitarUsuario();");
	deshabilitarUsuario();

	System.out.println("###listarComentariosYPuntuaciones();");
	listarComentariosYPuntuaciones();

	 System.out.println("###eliminarRatting();");
	 eliminarRatting();
	 in.close();
    }

    private void listarUsuariosSistema() throws Exception {

	System.out.println("Introduce Id de usuario");
	Long userId = in.nextLong();

	User user = cs.findUserById(userId);

	List<Trip> promotedTrips = cs.ListAllTripsPromotedByUser(userId);
	List<Trip> participatedTrips = cs
		.ListAllTripsWhereUserParticipated(userId);

	imprimirDatosUsuarioViajes(user, promotedTrips.size(),
		participatedTrips.size());

    }

    private void imprimirDatosUsuarioViajes(User user, int numberPromoted,
	    int numberParticipated) {

	System.out.println("\nId: " + user.getId() + "\nUsuario: "
		+ user.getLogin());

	System.out.println("\nPromocionados: " + numberPromoted
		+ "\nParticipa: " + numberParticipated);

    }

    private void deshabilitarUsuario() {
	
	System.out.println("Introduce Id de usuario");
	Long userId = in.nextLong();

	try {
	    cs.cancelUsuario(userId);
	} catch (EntityNotFoundException e) {
	    System.out.println(e.getMessage());
	}

    }

    private void listarComentariosYPuntuaciones() {

	System.out.println("Introduce Id de usuario");
	Long userId = in.nextLong();

	List<Trip> participatedTrips = cs
		.ListAllTripsWhereUserParticipated(userId);

	Date actual = new Date();
	Date date = getNewDateMonth(actual, -1);

	List<Trip> trips = new ArrayList<>();

	for (Trip t : participatedTrips)
	    if (t.getArrivalDate().after(date)
		    && t.getArrivalDate().before(actual))
		trips.add(t);
	List<Rating> ratings = null;
	List<RatingInfo> ri = new ArrayList<>();

	for (Trip t : trips) {
	    ratings = cs.findRatingsAboutTrip(t.getId());
	    for (Rating r : ratings)
		ri.add(new RatingInfo(r, t));
	}

	Coollection cool = new Coollection();

	ri = cool.from(ri).orderBy("fecha", Order.DESC).all();

	imprimirRatings(ri);

    }

    private void imprimirRatings(List<RatingInfo> ri) {
	System.out.println("----------ratings-----------------");

	for (RatingInfo r : ri) {
	    System.out.println("----------------------------------");
	    System.out.println("Destino: "
		    + r.getTrip().getDeparture().getCity()
		    + "\nComentario realizado por: "
		    + r.getRating().getSeatFromUserId()
		    + "\nSobre el usuario: "
		    + r.getRating().getSeatAboutUserId() + "\nValoración: "
		    + r.getRating().getValue() + "\nComentario: "
		    + r.getRating().getComment());
	    System.out.println("----------------------------------");
	}
    }

    // Le pasas una fecha y el tiempo y te devuelve esa fecha
    // Por ejemplo, fecha actual y -1 mes, te devuelve un date con la fecha
    // Del mes pasado
    private Date getNewDateMonth(Date actual, int months) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(actual);
	cal.add(Calendar.MONTH, months);
	return cal.getTime();
    }

    private void eliminarRatting() {
	System.out.println("Introduce Id del rating que desea eliminar");
	Long ratingId = in.nextLong();
	
	try {
	    cs.cancelRating(ratingId);
	} catch (EntityNotFoundException e) {
	   System.out.println(e.getMessage());
	}
	
    }

}
