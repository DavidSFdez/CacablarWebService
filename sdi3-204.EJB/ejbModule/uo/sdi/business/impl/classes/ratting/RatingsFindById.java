package uo.sdi.business.impl.classes.ratting;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Rating;
import uo.sdi.persistence.RatingDao;

public class RatingsFindById {

    RatingDao rd = Factories.persistence.createRattingDao();

    public Rating find(Long ratingId) {
	return rd.findById(ratingId);
    }

}
