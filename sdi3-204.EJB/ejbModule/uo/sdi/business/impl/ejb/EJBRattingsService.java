package uo.sdi.business.impl.ejb;

import javax.ejb.Stateless;
import javax.jws.WebService;

import uo.sdi.business.impl.classes.ratting.RatingsFindById;
import uo.sdi.business.impl.classes.ratting.RattingsFindByTrip;
import uo.sdi.business.impl.classes.ratting.RattingsDelete;
import uo.sdi.business.impl.local.LocalRattingsService;
import uo.sdi.business.impl.remote.RemoteRattingsService;
import uo.sdi.model.Rating;

@Stateless
@WebService(name = "RattingsService")
public class EJBRattingsService implements LocalRattingsService,
	RemoteRattingsService {

    @Override
    public Rating listByTrip(Long idTrip) {
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

}
