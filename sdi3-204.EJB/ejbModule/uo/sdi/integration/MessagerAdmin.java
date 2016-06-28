package uo.sdi.integration;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

import alb.util.log.Log;

@Stateless
public class MessagerAdmin {

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory factory;
    @Resource(mappedName = "java:/queue/msgAdmin")
    private Destination queue;
    @Resource
    private SessionContext ctx;

    private Connection con;
    private Session session;

    public void sendMessage(MapMessage mm) throws JMSException {
	init();
	send(mm);
	close();
    }

    private void init() {
	try {
	    con = factory.createConnection("sdi", "password");
	    session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
	} catch (JMSException e) {
	    Log.warn("Error on message Listener:" + e.getMessage());
	}
    }

    private void send(MapMessage mm) {
	MessageProducer sender;
	try {
	    sender = session.createProducer(queue);
	    sender.send(mm);
	} catch (JMSException e) {
	    Log.warn("Error on message Listener:" + e.getMessage());
	}

    }

    private void close() {
	try {
	    session.close();
	    con.close();
	} catch (JMSException e) {
	    Log.warn("Error on message Listener:" + e.getMessage());
	}
    }

}
