package uo.sdi.business.impl.classes.ratting;

import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Rating;
import uo.sdi.persistence.RatingDao;

public class RattingsFindByTrip {

    public List<Rating> find(Long idTrip) {

	RatingDao rd = Factories.persistence.createRattingDao();

	return rd.findByTrip(idTrip);
    }

}
