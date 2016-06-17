package uo.sdi.model.DTO;

import java.util.Date;

import uo.sdi.model.Rating;

public class RatingInfo {

    private Rating rating;
    private String destino;
    private Date arrivalDate;
    
    
    public RatingInfo(){}


    public Rating getRating() {
        return rating;
    }


    public void setRating(Rating rating) {
        this.rating = rating;
    }


    public String getDestino() {
        return destino;
    }


    public void setDestino(String destino) {
        this.destino = destino;
    }


    public Date getArrivalDate() {
        return arrivalDate;
    }


    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

  
    
}