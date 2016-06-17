package uo.sdi.business;

import java.util.List;

import uo.sdi.model.DTO.RatingInfo;
import uo.sdi.model.DTO.UserInfo;

public interface ClientService {

    List<UserInfo> listUsersInfo();
    
    void disableUser(Long userId);
    
    List<RatingInfo> listRatings(int numMonths);
    
    void removeRating(Long ratingId);
}
