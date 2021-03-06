package uo.sdi.business.impl.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import uo.sdi.business.impl.classes.ratting.RatingsFindById;
import uo.sdi.business.impl.classes.ratting.RatingsFindLastMonth;
import uo.sdi.business.impl.classes.ratting.RattingsDelete;
import uo.sdi.business.impl.classes.ratting.RattingsFindByTrip;
import uo.sdi.business.impl.local.LocalRattingsService;
import uo.sdi.business.impl.remote.RemoteRattingsService;
import uo.sdi.model.Rating;
import uo.sdi.model.DTO.RatingInfo;

@Stateless
@WebService(name = "RattingsService")
public class EJBRattingsService implements LocalRattingsService,
	RemoteRattingsService {

    @Override
    public List<Rating> listByTrip(Long idTrip) {
	return new RattingsFindByTrip().find(idTrip);
    }

    @Override
    public void delete(Long id) {
	new RattingsDelete().delete(id);

    }

    @Override
    public Rating findRatingById(Long ratingId) {
	return new RatingsFindById().find(ratingId);
    }

    @Override
    public List<RatingInfo> findRatingsLastMonth() {
	return new RatingsFindLastMonth().find();
    }

}
