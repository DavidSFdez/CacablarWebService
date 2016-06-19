package uo.sdi.persistence;

import java.util.Date;
import java.util.List;

import uo.sdi.model.Trip;
import uo.sdi.persistence.util.GenericDao;

public interface TripDao extends GenericDao<Trip, Long> {

    Trip findByPromoterIdAndArrivalDate(Long id, Date arrivalDate);

    List<Trip> findAllActive();

    List<Trip> findWhenParticipated(Long id);

    List<Trip> findAllActiveToUser(Long idUser);

    Trip findByIdAndPromoter(Long idTrip, Long idUser);

    Long updateTripsStatusToClose();

    List<Trip> findAllPromoted(Long id);

    List<Trip> findAllParticipated(Long id);

    List<Trip> findAllPromotedAndActive(long idUser);

}
