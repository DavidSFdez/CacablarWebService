package uo.sdi.model.DTO;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RatingInfo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8611846701284485685L;
    private Long aboutUserId;
    private Long fromUserId;
    private String comment;
    private int value;
    private String destino;

    public RatingInfo() {
    }

    public Long getAboutUserId() {
	return aboutUserId;
    }

    public void setAboutUserId(Long aboutUserId) {
	this.aboutUserId = aboutUserId;
    }

    public Long getFromUserId() {
	return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
	this.fromUserId = fromUserId;
    }

    public String getComment() {
	return comment;
    }

    public void setComment(String comment) {
	this.comment = comment;
    }

    public int getValue() {
	return value;
    }

    public void setValue(int value) {
	this.value = value;
    }

    public String getDestino() {
	return destino;
    }

    public void setDestino(String destino) {
	this.destino = destino;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

}
