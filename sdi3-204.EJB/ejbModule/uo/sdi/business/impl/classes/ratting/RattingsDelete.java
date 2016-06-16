package uo.sdi.business.impl.classes.ratting;

import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.persistence.RatingDao;
import uo.sdi.persistence.exception.NotPersistedException;

public class RattingsDelete {

    public void delete(Long id) throws EntityNotFoundException {
	RatingDao rd = Factories.persistence.createRattingDao();
	try {
	    rd.delete(id);
	} catch (NotPersistedException e) {
	    throw new EntityNotFoundException(
		    "No existe la valoración a borrar.", e);
	}
    }

}
