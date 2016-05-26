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

    public void sendMessage(List<User> users, MapMessage men)
	    throws JMSException {
	init();
	send(users, men);
    }

    private void init() {
	try {
	    con = factory.createTopicConnection("sdi", "password");
	    session = con.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
	} catch (JMSException e) {
	    e.printStackTrace();
	}
    }

    private void send(List<User> users, MapMessage men) throws JMSException {
	Long tripId = men.getLong("tripId");
	String message = men.getString("message");

	Map<String, Object> msg = new HashMap<String, Object>();
	msg.put("mensaje", message);
	msg.put("trip", tripId);
	for (User user : users) {
	    // id destinatario
	    msg.put("user", user.getId());
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
	msg.setString("mensaje", (String) map.get("mensaje"));
	msg.setLong("userId", (Long) map.get("user"));
	msg.setLong("tripId", (Long) map.get("trip"));
	return msg;
    }

}
