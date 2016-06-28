package uo.sdi.integration;

import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import alb.util.log.Log;
import uo.sdi.business.TripsService;
import uo.sdi.business.UsersService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.business.impl.local.LocalTripsService;
import uo.sdi.business.impl.local.LocalUsersService;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;
import uo.sdi.model.User;

/*
 * Los mensajes se envian a aquí y se mandan al canal adecuado
 *
 */
@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/msg") })
public class SDIListener implements MessageListener {

    @EJB(beanInterface = LocalUsersService.class)
    private UsersService service;

    @EJB(beanInterface = LocalTripsService.class)
    private TripsService tripService;

    @EJB
    MessageSender messageSender;
    @EJB
    MessagerAdmin messagerAdmin;

    @Override
    public void onMessage(Message msg) {
	try {
	    Log.info("on message");

	    processMessage(msg);
	} catch (JMSException | BusinessException
		| EntityAlreadyExistsException | EntityNotFoundException e) {
	    Log.warn("Error on message Listener.");
	    // e.printStackTrace();
	}
    }

    private void processMessage(Message msg) throws BusinessException,
	    JMSException, EntityAlreadyExistsException, EntityNotFoundException {

	if (!messageOfExpectedType(msg)) {
	    Log.warn("Not of expected type " + msg);
	    return;
	}

	MapMessage mm = (MapMessage) msg;
	Long tripId = mm.getLong("tripId");
	Long userId = mm.getLong("userId");

	List<User> pasajeros = service.findUsersOnTripByStatus(tripId,
		SeatStatus.ADMITIDO);

	// El que manda el mensaje
	User user = service.findUserById(userId);

	// El viaje al que lo manda
	Trip trip = tripService.findTripById(tripId);

	if (trip == null) {
	    Log.warn("No such trip.");
	    messagerAdmin.sendMessage(mm);
	    return;
	}

	if (pasajeros.contains(user)) {
	    // Se quita al propio usuario para que no se lo mande a si mismo
	    pasajeros.remove(user);
	    // Se añade al promotor
	    pasajeros.add(service.findUserById(trip.getPromoterId()));
	    // Mandar al topic
	    messageSender.sendMessage(user.getId(), pasajeros, mm);
	    return;
	}

	if (trip.getPromoterId().equals(userId)) {
	    // Si el promotor es el que manda el mensaje se manda a todos los
	    // pasajeros
	    messageSender.sendMessage(user.getId(), pasajeros, mm);
	    return;
	}

	// Mandar a la cola de adminisracion
	messagerAdmin.sendMessage(mm);

    }

    private boolean messageOfExpectedType(Message msg) {
	return msg instanceof MapMessage;
    }

}
