package uo.sdi.business.impl.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.business.impl.classes.maintenance.ApplicationsYSeatsActualizarAutomaticamente;
import uo.sdi.business.impl.classes.seat.ApplicationCancel;
import uo.sdi.business.impl.classes.seat.ApplicationsAccept;
import uo.sdi.business.impl.classes.seat.ApplicationsFind;
import uo.sdi.business.impl.classes.seat.ApplicationsRemove;
import uo.sdi.business.impl.local.LocalApplicationsService;
import uo.sdi.business.impl.remote.RemoteApplicationsService;
import uo.sdi.model.Application;

@Stateless
@WebService(name = "ApplicationsService")
public class EJBApplicationsService implements LocalApplicationsService,
	RemoteApplicationsService {

    @Override
    public Application findApplication(Long idTrip, Long idUser) {
	return new ApplicationsFind().find(idTrip, idUser);
    }

    @Override
    public void remove(Long idUser, Long idTrip) throws EntityNotFoundException {
	new ApplicationsRemove().remove(idUser, idTrip);

    }
    

    /**
     *  @deprecated
     * Devuelve todas las Applications que pertenecen a viajes que NO 
     * se encuentran en estado 0 (OPEN)
     * 
     * Aparte de devolver estas solicitudes, LAS BORRA
     * 
     * La razón de hacer esto es que el enunciado dice que cuando se cierre 
     * un viaje las solicitudes pendientes han de ponerse en seats con el estado
     * 4 (SIN_PLAZA)
     * 
     */
    @Override
    public List<Application> getApplicationsWithTripClosedToUpdate() {
	return new ApplicationsYSeatsActualizarAutomaticamente().find();
    }

    @Override
    public void acceptApplication(Application application)
	    throws EntityNotFoundException, EntityAlreadyExistsException {
	new ApplicationsAccept().execute(application);
    }

    @Override
    public void cancelApplication(Application application)
	    throws EntityNotFoundException, EntityAlreadyExistsException {
	new ApplicationCancel().execute(application);
    }
}
