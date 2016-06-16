package uo.sdi.business;

import java.util.List;

import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.model.Rating;

public interface RattingsService {

    List<Rating> listByTrip(Long idTrip);

    void delete(Long long1) throws EntityNotFoundException;

    Rating findRatingById(Long ratingId);

}
