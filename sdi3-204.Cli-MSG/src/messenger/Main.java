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

public class Main {
    // PARA QQUE FUNCIONE LA MENSAJERÍA:
    // En el servidor poner en el fichero <<application-roles.properties>>
    // el rol guest en sdi
    // sdi=NotaneitorAdmin,Administrador,Invitado,guest

    private static final String JMS_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String MESSAGE_TOPIC = "jms/topic/msg";
    private static final String MESSAGE_QUEUE = "jms/queue/msg";

    private Connection con;
    private Session session;
    private MessageProducer sender;
    @SuppressWarnings("unused")
    private MessageConsumer consumer;

    public static void main(String[] args) throws Exception {
	new Main().run();
    }

    private Long tripId;
    private Long userId;
    private static ThreadLocal<User> UserLocal = new ThreadLocal<>();
    public static User getCUrrentUser(){
	return UserLocal.get();
    }

    @SuppressWarnings("static-access")
    private void run() throws Exception {
	LoginService ls = new RemoteEJBServiceLocator().createLoginService();
	@SuppressWarnings("resource")
	Scanner in = new Scanner(System.in);
	System.out.println("Nombre de usuario:");
	String login = in.next();
	System.out.println("Contraseña:");
	String password = in.next();
	User user = ls.verify(login, password);
	if (user != null) {
	    this.userId = user.getId();
	    this.UserLocal.set(user);
	    List<Trip> trips;
	    System.out.println("Selecciona el viaje");
	    try {
		trips = new RemoteEJBServiceLocator().createTripsService()
			.listRelated(userId);
	    } catch (Exception e) {
		throw new Exception();
	    }
	    List<Long> longs = new LinkedList<>();
	    for (Trip t : trips) {
		System.out.println(t.getId());
		longs.add(t.getId());
	    }
	    System.out.println("Seleccione viaje:");
	    Long idViaje = in.nextLong();
	    if (!longs.contains(idViaje))
		throw new Exception();

	    this.tripId = idViaje;

	    init();
	    //Aqui se consumen los mensaje

	    System.out.println("¿Desea mandar textos? (Y/N)");
	    if (in.next().equalsIgnoreCase("y")) {
		System.out.println("Escriba [quit] para salir");
		while (true) {
		    String texto = in.next();
		    if (texto.equalsIgnoreCase("quit"))
			break;
		    sendMessage(texto);
		}
	    }
	    close();
	} else
	    throw new Exception();
    }

    public void init() throws JMSException {
	ConnectionFactory factory = (ConnectionFactory) Jndi
		.find(JMS_CONNECTION_FACTORY);
	Destination topic = (Destination) Jndi.find(MESSAGE_TOPIC);
	Destination queue = (Destination) Jndi.find(MESSAGE_QUEUE);
	con = factory.createConnection("sdi", "password");
	Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
	MessageConsumer consumer = session.createConsumer(topic);
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
	    e.printStackTrace();
	}
    }

    private void sendMessage(String string) throws JMSException {
	MapMessage msg = session.createMapMessage();
	msg.setString("message", string);
	msg.setLong("tripId", tripId);
	msg.setLong("userId", userId);
	sender.send(msg);

    }

}
