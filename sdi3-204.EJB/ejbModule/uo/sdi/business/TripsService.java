package uo.sdi.business;

import java.util.List;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.model.Trip;

public interface TripsService {

    List<Trip> listActive();
    List<Trip> listRelated(Long idUser) throws Exception;
    List<Trip> listActiveToUser(Long idUser) throws Exception;
    void update(Trip trip, Long idUser) throws EntityNotFoundException;
    void cancel(Trip trip,Long idUser) throws EntityNotFoundException, EntityAlreadyExistsException;
    void save(Trip trip,Long idUser) throws EntityAlreadyExistsException;
    Trip findByIdandPromoter(Long idTrip, Long idUser);
    Trip findById(Long idTrip) throws EntityNotFoundException;
    void updateTripsStatus();
    List<Trip> findAllPromoted(Long id);
    List<Trip> findAllParticipated(Long id);
    List<Trip> findAll();
    List<Trip> findAllPromotedAndActive(long idUser);
 
    
   
}
