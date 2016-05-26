package messenger;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import static java.lang.System.out;

public class CCBMessageListener implements MessageListener {

    @Override
    public void onMessage(Message msg) {
	try {
	    processMessage(msg);
	} catch (JMSException e) {
	    e.printStackTrace();
	}
    }

    private void processMessage(Message msg) throws JMSException {
	if (!(msg instanceof MapMessage)) {
	    System.out.println("Message not of expected type");
	    return;
	}
	MapMessage mmsg = (MapMessage) msg;
	out.print(mmsg.getLong("userId"));
	out.print(": ");
	out.println(mmsg.getString("message"));
    }

}
