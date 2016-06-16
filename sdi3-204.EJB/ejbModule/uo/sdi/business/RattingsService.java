package uo.sdi.business;

import uo.sdi.model.Rating;

public interface RattingsService {

    Rating listByTrip(Long idTrip);

    void delete(Long long1);

    Rating findRatingById(Long ratingId);

}
