package uo.sdi.business.impl.classes.application;

import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.persistence.exception.NotPersistedException;

public class ApplicationsRemove {

    public void remove(Long idUser, Long idTrip) throws EntityNotFoundException {
	Long[] ids = { idUser, idTrip };

	try {
	    Factories.persistence.createApplicationDao().delete(ids);
	} catch (NotPersistedException e) {
	    throw new EntityNotFoundException(
		    "No existe la peticion que quiere borrar.", e);
	}

    }

}
