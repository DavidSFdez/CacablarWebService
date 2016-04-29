package uo.sdi.business.impl.classes.application;

import uo.sdi.infrastructure.Factories;
import uo.sdi.persistence.exception.NotPersistedException;

public class ApplicationsRemove {

    public void remove(Long idUser, Long idTrip) {
	Long[]ids = { idUser,idTrip};
	
	try {
	    Factories.persistence.createApplicationDao().delete(ids);
	} catch (NotPersistedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
    }

}
