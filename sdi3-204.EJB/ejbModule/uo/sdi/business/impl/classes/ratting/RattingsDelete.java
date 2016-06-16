package uo.sdi.business.impl.classes.ratting;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Rating;
import uo.sdi.persistence.RatingDao;
import uo.sdi.persistence.exception.NotPersistedException;

public class RattingsDelete {

    public void delete(Long id) {

	RatingDao rd = Factories.persistence.createRattingDao();

	try {
	    rd.delete(id);
	} catch (NotPersistedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

}
