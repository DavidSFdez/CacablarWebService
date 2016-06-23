package uo.sdi.business.impl.classes.ratting;

import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.DTO.RatingInfo;
import uo.sdi.persistence.RatingDao;

public class RatingsFindLastMonth {

    public List<RatingInfo> find() {
	RatingDao rd = Factories.persistence.createRattingDao();
	return rd.findLastMonth();
    }

}
