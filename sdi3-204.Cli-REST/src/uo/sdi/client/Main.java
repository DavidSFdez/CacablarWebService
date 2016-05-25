package uo.sdi.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

public class Main {

    private static final String REST_SERVICE_URL = "http://localhost:8280/Notaneitor_v6.0Web/rest/UsersServiceRs";

    CacablarRestService client;

    public static void main(String[] args) {
	new Main().run();
    }

    private void run() {

	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	client = new ResteasyClientBuilder().build()
		.register(new Authenticator("sdi", "password")).target("link")
		.proxy(CacablarRestService.class);
	try {

	    System.out.println("Introduzca id del viaje: ");
	    String usuario = in.readLine().trim();

	    System.out.println("Introduzca id del viaje: ");
	    String password = in.readLine().trim();

	    // login
	    client.login(usuario, password);

	    listTrips(client.listPromotedActiveTrips());

	    String[] id;

	    System.out.println("Introduzca id del viaje: ");
	    id = in.readLine().split(" ");
	    long idTrip = Long.parseLong(id[0]);
	    List<Application> applications = ListApplications(client
		    .selectTrip(idTrip));

	    System.out
		    .println("Seleccione el/los usuario/s que desea aceptar (separados por ',':");
	    String[] ids;

	    ids = in.readLine().split(",");

	    List<Long> idUsers = new ArrayList<>();

	    for (int i = 0; i < ids.length; i++)
		idUsers.add(Long.parseLong(ids[i]));

	    List<Application> selected = new ArrayList<>();

	    for (Application a : applications)
		for (Long i : idUsers)
		    if (a.getUserId().equals(i))
			selected.add(a);

	    for (Application a : selected)
		client.acceptSeat(a);

	} catch (IOException e) {

	    e.printStackTrace();
	}

    }

    private List<Application> ListApplications(List<Application> selectTrip) {
	printApplicationHeader();
	for (Application a : selectTrip)
	    printApplicationLine(a);
	return selectTrip;
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
	System.out.printf("%s %s %s\n", t.getDepartureDate().toString(), t
		.getDeparture().getCity(), t.getDestination().getCity());
    }

    private void printTripHeader() {
	System.out.printf("%s %s %s\n", "_FECHA______", "_ORIGEN_____",
		"_DESTINO_____");
    }
}
