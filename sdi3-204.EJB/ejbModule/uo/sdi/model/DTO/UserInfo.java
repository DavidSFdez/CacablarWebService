package uo.sdi.model.DTO;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import uo.sdi.model.UserStatus;

@XmlRootElement
public class UserInfo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1821081568348658031L;
    private String name;
    private String surname;
    private String email;
    private UserStatus status;
    private int numPromoted;
    private int numParticipated;
    private Long id;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public UserInfo() {
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getSurname() {
	return surname;
    }

    public void setSurname(String surname) {
	this.surname = surname;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public UserStatus getStatus() {
	return status;
    }

    public void setStatus(UserStatus status) {
	this.status = status;
    }

    public int getNumPromoted() {
	return numPromoted;
    }

    public void setNumPromoted(int numPromoted) {
	this.numPromoted = numPromoted;
    }

    public int getNumParticipated() {
	return numParticipated;
    }

    public void setNumParticipated(int numParticipated) {
	this.numParticipated = numParticipated;
    }

}
