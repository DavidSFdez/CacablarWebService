package uo.sdi.business.impl.classes.ratting;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Rating;
import uo.sdi.persistence.RatingDao;

public class RattingsFindByTrip {

    public Rating find(Long idTrip) {

	RatingDao rd = Factories.persistence.createRattingDao();
	
	return rd.findByTrip(idTrip);
    }

}
