package messenger;

import static java.lang.System.out;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import uo.sdi.model.User;

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
	User user = Main.getCurrentUser();
	Long tripID = Main.getCurrentTrip();
	
	if (user == null)
	    return;
	
	MapMessage mmsg = (MapMessage) msg;
	if ( isDebbugOn() || 
		(mmsg.getLong("userId") == user.getId() && mmsg.getLong("tripId") == tripID)) {
	    out.print(mmsg.getLong("from"));
	    out.print(": ");
	    out.println(mmsg.getString("message"));
	}
    }
    
    private boolean isDebbugOn(){
	return Main.DEBBUG_MESSAGES;
    }

}
