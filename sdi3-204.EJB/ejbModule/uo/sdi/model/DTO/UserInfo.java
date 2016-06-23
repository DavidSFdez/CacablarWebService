package uo.sdi.model.DTO;

import java.io.Serializable;

import uo.sdi.model.User;

public class UserInfo implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1821081568348658031L;
    private User user;
    private int numPromoted;
    private int numParticipated;
    
    public UserInfo(){}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
