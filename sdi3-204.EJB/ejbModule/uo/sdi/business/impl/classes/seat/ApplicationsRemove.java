package uo.sdi.business.impl.classes.seat;

import uo.sdi.infrastructure.Factories;
import uo.sdi.persistence.exception.NotPersistedException;
import alb.util.log.Log;

public class ApplicationsRemove {

    public void remove(Long idUser, Long idTrip) {
	Long[] ids = { idUser, idTrip };

	try {
	    Factories.persistence.createApplicationDao().delete(ids);
	} catch (NotPersistedException e) {
	    Log.warn("No existe la peticion que quiere borrar.");
	}

    }

}
