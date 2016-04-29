package uo.sdi.business.impl.classes.application;

import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Application;

public class ApplicationsFind {

    public Application find(Long id, Long idUser)
	    throws EntityNotFoundException {
	Long[] ids = { idUser, id };

	Application a = Factories.persistence.createApplicationDao().findById(
		ids);
	if (a == null)
	    throw new EntityNotFoundException(
		    "No existe solicitud del usuario " + idUser
			    + " para el viaje " + id);
	return a;

    }

}
