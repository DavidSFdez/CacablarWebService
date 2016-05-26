package messenger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

import util.Jndi;

public class Main {
    // PARA QQUE FUNCIONE LA MENSAJERÍA:
    // En el servidor poner en el fichero <<application-roles.properties>>
    // el rol guest en sdi
    // sdi=NotaneitorAdmin,Administrador,Invitado,guest

    public static void main(String[] args) throws JMSException {
	new Main().run();
    }

    private static final String JMS_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String MESSAGE_QUEUE = "jms/queue/msg";

    private Connection con;
    private Session session;
    private MessageProducer sender;
    private Long tripId;
    private Long userId;

    private void run() throws JMSException {
	init();

	// TODO
	// Loguearse
	//
	// this.userId = usuariologeado.getId();
	//
	// if(logueado){
        	// listar sus viajes activos (participa o pormotor)
        	// seleccona uno
        	//
        	// this.tripId = viajeseleccionado.getId();
        	//
        	// mostar por pantalla todos los mensajes de ese viaje con el ID del
        	// usuario logueado que hay en topic/msg
        	// if(preguntar si quiere escribir == "SI"){
                	// while(no escriba SALIR o algo asi){
                        	// Streing mesahiyo = leer por pantalla mensaje
                        	// sendMessage(mesahiyo);
                	// }
        	// }
	// }

	close();
    }

    private void init() throws JMSException {
	ConnectionFactory factory = (ConnectionFactory) Jndi
		.find(JMS_CONNECTION_FACTORY);
	Destination queue = (Destination) Jndi.find(MESSAGE_QUEUE);
	con = factory.createConnection("sdi", "password");
	session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
	sender = session.createProducer(queue);
	con.start();
    }

    private void close() {
	try {
	    session.close();
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
