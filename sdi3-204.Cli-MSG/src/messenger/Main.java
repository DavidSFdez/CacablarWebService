package messenger;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

import uo.sdi.business.LoginService;
import uo.sdi.business.impl.RemoteEJBServiceLocator;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import util.Jndi;
import static java.lang.System.out;
import static java.lang.System.err;

public class Main {

    private static final String JMS_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String MESSAGE_TOPIC = "jms/topic/msg";
    private static final String MESSAGE_QUEUE = "jms/queue/msg";

    public final static boolean DEBBUG_MESSAGES = true;

    private Connection con;
    private Session session;
    private MessageProducer sender;
    private MessageConsumer consumer;

    LoginService ls = new RemoteEJBServiceLocator().getLoginService();

    private Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
	if (DEBBUG_MESSAGES)
	    out.println("Modo debugg activo.");
	Main m = new Main();
	try {
	    m.run();
	} catch (Exception e) {
	    out.println(e.getMessage());
	}
    }

    // Como no se pueden mandar al listener parametros por constructor se
    // almacenan en el hilo
    // en esta clase

    private static User currentUser;
    private static Long currentTripId;

    public static User getCurrentUser() {
	return currentUser;
    }

    public static Long getCurrentTrip() {
	return currentTripId;
    }

    private void run() throws Exception {
	String login = pedirDato("Nombre de usuario:");
	String pass = pedirDato("Contraseña:");

	User user = ls.verify(login, pass);

	if (user == null)
	    throw new Exception("Usuario incorrecto.");

	Long userId = user.getId();
	Long tripId = seleccionarViaje(userId);

	currentTripId = (tripId);

	currentUser = (user);

	init();
	// Aqui aparecen los mensajes del viaje seleccionado automaticamente
	// porque el
	// listener que imprime los mensajes se inicializa en este momento.

	enviar: {
	    String text = pedirDato("¿Desea mandar mensajes a este viaje? (Y/N)");

	    if (!text.equalsIgnoreCase("y"))
		break enviar;

	    out.println("Escriba los mensajes que quiera enviar a ese viaje.");
	    out.println("Escriba .quit para salir.");
	    while (true) {
		text = in.next();
		if (text.equalsIgnoreCase(".quit"))
		    break;
		sendMessage(text, tripId, userId);
	    }
	}
	close();
	out.println("Buen viaje.");
    }

    private Long seleccionarViaje(Long userId) throws Exception {
	out.println("Sus viajes:");

	List<Trip> trips = new RemoteEJBServiceLocator().getTripsService()
		.listRelated(userId);

	if (trips.isEmpty())
	    throw new Exception("No tiene viajes disponibles.");

	List<Long> longs = new LinkedList<>();

	for (Trip trip : trips) {
	    longs.add(trip.getId());
	    out.print("ID: ");
	    out.print(trip.getId());
	    out.print("\t Destino: ");
	    out.println(trip.getDestination().getCity());
	}

	String selected = pedirDato("selecciona un viaje:");

	if (selected.matches("\\d+")) {
	    Long selectedID = Long.parseLong(selected);
	    if (longs.contains(selectedID))
		return selectedID;
	}

	throw new Exception("Viaje inválido.");
    }

    public void init() throws JMSException {
	ConnectionFactory factory = (ConnectionFactory) Jndi
		.find(JMS_CONNECTION_FACTORY);
	Destination topic = (Destination) Jndi.find(MESSAGE_TOPIC);
	Destination queue = (Destination) Jndi.find(MESSAGE_QUEUE);
	con = factory.createConnection("sdi", "password");
	session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
	consumer = session.createConsumer(topic);
	consumer.setMessageListener(new CCBMessageListener());
	sender = session.createProducer(queue);
	con.start();
    }

    private void close() {
	try {
	    if (session != null)
		session.close();
	    if (con != null)
		con.close();
	} catch (JMSException e) {
	    err.println("Error: " + e.getMessage());
	}
    }

    private void sendMessage(String string, long tripId, long userId)
	    throws JMSException {
	MapMessage msg = session.createMapMessage();
	msg.setString("message", string);
	msg.setLong("tripId", tripId);
	msg.setLong("userId", userId);
	sender.send(msg);

    }

    private String pedirDato(String msg) {
	out.println(msg);
	return in.next();
    }

}
