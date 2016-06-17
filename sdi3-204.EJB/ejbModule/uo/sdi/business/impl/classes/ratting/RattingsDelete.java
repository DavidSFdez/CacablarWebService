package uo.sdi.business.impl.classes.ratting;

import uo.sdi.infrastructure.Factories;
import uo.sdi.persistence.RatingDao;
import uo.sdi.persistence.exception.NotPersistedException;
import alb.util.log.Log;

public class RattingsDelete {

    public void delete(Long id) {
	RatingDao rd = Factories.persistence.createRattingDao();
	try {
	    rd.delete(id);
	} catch (NotPersistedException e) {
	    Log.warn("No existe la valoración a borrar.");
	    // throw new
	    // EntityNotFoundException("No existe la valoración a borrar.", e);
	}
    }

}
