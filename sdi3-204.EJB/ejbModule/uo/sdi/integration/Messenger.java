package uo.sdi.integration;

import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import uo.sdi.business.UsersService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.business.impl.local.LocalUsersService;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.User;

/*
 * Los mensajes se envian a aquí y se mandan al canal adecuado
 *
 */
@MessageDriven(
	activationConfig = {
		@ActivationConfigProperty(
			propertyName = "destination", 
			propertyValue = "queue/msg") 
		})
public class Messenger implements MessageListener {

    @EJB(beanInterface = LocalUsersService.class)
    private UsersService service;
    
    @EJB MessageSender messageSender;
    @EJB MessagerAdmin messagerAdmin;

    @Override
    public void onMessage(Message msg) {
	try {
	    processMessage(msg);
	} catch (JMSException | BusinessException
		| EntityAlreadyExistsException | EntityNotFoundException e) {
	    e.printStackTrace();
	}
    }

    private void processMessage(Message msg) throws BusinessException,
	    JMSException, EntityAlreadyExistsException, EntityNotFoundException {
	if (!messageOfExpectedType(msg)) {
	    System.out.println("Not of expected type " + msg);
	    return;
	}
	MapMessage mm = (MapMessage) msg;
	Long tripId = mm.getLong("tripId");
	Long userId = mm.getLong("userId");

	List<User> pasajeros = service.findUsersOnTripByStatus(tripId,
		SeatStatus.ADMITIDO);
	User user = service.findUserById(userId);

	if (pasajeros.contains(user)) {
	    //Se quita al propio usuario para que no se lo mande a si mismo
	    pasajeros.remove(user);
	    // Mandar al topic
	    messageSender.sendMessage(pasajeros, mm);
	} else {
	    // Mandar a la cola de adminisracion
	    messagerAdmin.sendMessage(mm);
	}
    }

    private boolean messageOfExpectedType(Message msg) {
	return msg instanceof MapMessage;
    }

}
