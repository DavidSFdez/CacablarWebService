package uo.sdi.business.maintenance;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;

import uo.sdi.infrastructure.Factories;
import alb.util.log.Log;

@Singleton
@Startup
public class EJBMantenimiento {

    @Schedule(second = "10", minute = "*", hour = "*")
    public void runTask1() {
	Log.trace("Actualizando viajes y asientos.");

	// Todos los viajes que se hayan acabado sus plazas disponibles
	// o que haya pasado la fecha de cierre
	// cambia su estado a 1 (CLOSED)
	Factories.services.getTripsService().updateTripsStatus();

	// Actualiza todas las aplicaciones de estos viajes (explicado en la
	// própia
	// clase de negocio el funcionamiento exacto)
	Factories.services.getSeatsService()
		.actualizarAsientosAutomaticamente();

    }

    @Timeout
    public void timeout(){
 	// Nada
     }
}
