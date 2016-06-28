package uo.sdi.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import uo.sdi.model.Application;
import uo.sdi.model.Trip;

public class Main {

    private static final String REST_SERVICE_URL = "http://localhost:8180/sdi3-204.Web/rest/";

    SdiRestService client;

    public static void main(String[] args) {
	new Main().run();
    }

    private void run() {

	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	try {

	    System.out.println("Nombre de usuario: ");
	    String usuario = in.readLine().trim();

	    System.out.println("Contraseña: ");
	    String password = in.readLine().trim();

	    client = new ResteasyClientBuilder().build()
		    .register(new Authenticator(usuario, password))
		    .target(REST_SERVICE_URL).proxy(SdiRestService.class);

	    System.out.println("-----");

	    List<Trip> trips = client.tripsPromoted();

	    listTrips(trips);
	    System.out.println("Escoja un viaje: ");
	    String tripId = in.readLine().trim();
	    Long tId = null;
	    try {
		tId = Long.parseLong(tripId);
	    } catch (NumberFormatException e) {
		System.out.println(e.getMessage());
	    }
	    List<Application> applications = client.getApplicationsByTrip(tId);
	    if (applications.size() > 0) {
		listApplications(applications);

		while (applications.size() > 0) {
		    System.out
			    .println("escoja usuario para aceptar (exit para salir)");
		    String userId = in.readLine().trim();
		    switch (userId) {
		    case "exit":
			return;
		    default:
			try {
			    Long uId = Long.parseLong(userId);
			    client.aceptApplications(new Application(uId, tId));
			} catch (NumberFormatException e) {
			    System.out.println(e.getMessage());
			}
			break;
		    }
		}

	    }// fin

	} catch (IOException e) {
	    System.err.println("Error: "+ e.getMessage());
	}

    }

    private void listApplications(List<Application> selectTrip) {
	printApplicationHeader();
	for (Application a : selectTrip)
	    printApplicationLine(a);
    }

    private void printApplicationHeader() {
	System.out.printf("%s \n", "_USER_ID______");

    }

    private void printApplicationLine(Application a) {
	System.out.printf("%d\n", a.getUserId());

    }

    private void listTrips(List<Trip> listPromotedActiveTrips) {

	printTripHeader();
	for (Trip t : listPromotedActiveTrips)
	    printTripLine(t);
    }

    private void printTripLine(Trip t) {
	System.out.printf("%d %s %s %s\n", t.getId(), t.getDepartureDate()
		.toString(), t.getDeparture().getCity(), t.getDestination()
		.getCity());
    }

    private void printTripHeader() {
	System.out.printf("%s %s %s %s\n", "_ID__", "_FECHA__________",
		"_ORIGEN_________", "_DESTINO_________");
    }
}
