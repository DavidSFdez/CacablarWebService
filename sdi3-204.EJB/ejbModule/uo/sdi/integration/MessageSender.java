package uo.sdi.integration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;

import uo.sdi.model.User;

@Stateless
public class MessageSender {

    @Resource(mappedName = "java:/ConnectionFactory")
    private TopicConnectionFactory factory;
    @Resource(mappedName = "java:/topic/msg")
    private Destination queue;
    @Resource
    private SessionContext ctx;

    private TopicConnection con;
    private TopicSession session;

    public void sendMessage(Long from, List<User> users, MapMessage men)
	    throws JMSException {
	init();
	send(from, users, men);
	close();
    }

    private void init() {
	try {
	    con = factory.createTopicConnection("sdi", "password");
	    session = con.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
	} catch (JMSException e) {
	    e.printStackTrace();
	}
    }

    private void send(Long from, List<User> users, MapMessage men) throws JMSException {
	Long tripId = men.getLong("tripId");
	String message = men.getString("message");

	Map<String, Object> msg = new HashMap<String, Object>();
	msg.put("message", message);
	msg.put("tripId", tripId);
	msg.put("from", from);
	for (User user : users) {
	    // id destinatario
	    msg.put("userId", user.getId());
	    send(msg);
	}
    }

    private void send(Map<String, Object> map) {
	MessageProducer sender;
	try {
	    sender = session.createProducer(queue);
	    MapMessage msg = createJmsMapMessage(map, session);
	    sender.send(msg);
	} catch (JMSException e) {
	    e.printStackTrace();
	}
    }

    private MapMessage createJmsMapMessage(Map<String, Object> map,
	    TopicSession topicSession) throws JMSException {
	MapMessage msg = session.createMapMessage();
	msg.setString("message", (String) map.get("message"));
	msg.setLong("userId", (Long) map.get("userId"));
	msg.setLong("tripId", (Long) map.get("tripId"));
	msg.setLong("from", (Long) map.get("from"));
	return msg;
    }

    private void close() {
	try {
	    session.close();
	    con.close();
	} catch (JMSException e) {
	    e.printStackTrace();
	}
    }
}
