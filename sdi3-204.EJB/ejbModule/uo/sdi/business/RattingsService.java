package uo.sdi.business;

import java.util.List;

import uo.sdi.model.Rating;
import uo.sdi.model.DTO.RatingInfo;

public interface RattingsService {

    List<Rating> listByTrip(Long idTrip);

    void delete(Long long1);

    Rating findRatingById(Long ratingId);

    List<RatingInfo> findRatingsLastMonth();

}
