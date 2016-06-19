package uo.sdi.business.impl.classes.trip;

import uo.sdi.infrastructure.Factories;


/**
 * Actualiza el estado de los viajes que hayan pasado o que, por algún motivo
 * No se encuentren en el estado correcto en la BBDD (no queden asientos
 * pero esté en estado abierto
 * 
 */
public class TripsUpdateStatusToClose {

    public void update() {

	Factories.persistence.createTripDao().updateTripsStatusToClose();

    }

}
