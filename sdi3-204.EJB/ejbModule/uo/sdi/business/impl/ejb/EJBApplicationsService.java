package uo.sdi.business.impl.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.business.impl.classes.application.ApplicationCancel;
import uo.sdi.business.impl.classes.application.ApplicationsAccept;
import uo.sdi.business.impl.classes.application.ApplicationsFind;
import uo.sdi.business.impl.classes.application.ApplicationsGetToUpdate;
import uo.sdi.business.impl.classes.application.ApplicationsRemove;
import uo.sdi.business.impl.local.LocalApplicationsService;
import uo.sdi.business.impl.remote.RemoteApplicationsService;
import uo.sdi.model.Application;

@Stateless
@WebService(name = "ApplicationsService")
public class EJBApplicationsService implements LocalApplicationsService,
	RemoteApplicationsService {

    @Override
    public Application findApplication(Long idTrip, Long idUser){
	return new ApplicationsFind().find(idTrip, idUser);
    }

    @Override
    public void remove(Long idUser, Long idTrip) throws EntityNotFoundException {
	new ApplicationsRemove().remove(idUser, idTrip);

    }

    @Override
    public List<Application> getToUpdate() {
	return new ApplicationsGetToUpdate().find();
    }

    @Override
    public void acceptApplication(Application application)
	    throws EntityAlreadyExistsException, EntityNotFoundException {

	new ApplicationsAccept().execute(application);

    }

    @Override
    public void cancelApplication(Application application)
	    throws EntityAlreadyExistsException, EntityNotFoundException {
	new ApplicationCancel().execute(application);
    }
}
