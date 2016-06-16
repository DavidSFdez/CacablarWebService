package uo.sdi.presentation;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import alb.util.log.Log;
import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Application;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;

@ManagedBean(name = "trip")
@RequestScoped
public class TripBean implements Serializable {

    private static final long serialVersionUID = 1L;

    Trip trip;

    @ManagedProperty("#{param.id}")
    String id;

    public TripBean() {
	trip = new Trip();
    }

    @PostConstruct
    private void actualizar() {
	Log.trace("Cargando viaje.");
	// Cargar el viaje con ID que venga en los parametros
	cargarViaje: {

	    Log.debug("Id de viaje en petición: [" + id + "].");

	    if (null == id) {
		Log.debug("No hay ID.");
		break cargarViaje;
	    }
	    try {
		actualizarTrip();
	    } catch (NumberFormatException e) {
		Log.error("El ID no es un número.");
		// El id no era un número
	    } catch (EntityNotFoundException e) {
		Log.error("El ID no pertenece a ningun viaje.");
		// No existe tal viaje
	    }
	}

	Log.trace("Actualizando asientos.");
	Factories.services.getTripsService().updateTripsStatus();
	List<Application> applications = Factories.services
		.getApplicationService().getToUpdate();
	try {
	    Factories.services.getSeatsService().seatsToUpdate(applications);
	} catch (EntityAlreadyExistsException e) {
	    Log.trace("Se ha intentado actualizar un asiento ya actuaizado.");
	}

    }

    public boolean isPromoter(Long idUser) {
	return Factories.services.getTripsService().findByIdandPromoter(
		trip.getId(), idUser) == null ? false : true;
    }

    public boolean isInApplications(Long idUser) {

	Application application = Factories.services.getApplicationService()
		.findApplication(trip.getId(), idUser);
	return application != null;

    }

    public boolean isInSeats(Long idUser) {
	try {
	    Factories.services.getSeatsService().findByUserAndTrip(idUser,
		    trip.getId());

	} catch (EntityNotFoundException e) {
	    return false;
	}
	return true;

    }

    public boolean isSitting(Long idUser) {

	try {
	    Seat seat = Factories.services.getSeatsService().findByUserAndTrip(
		    idUser, trip.getId());
	    if (!seat.getStatus().equals(SeatStatus.ADMITIDO))
		return false;
	} catch (EntityNotFoundException e) {
	    return false;
	}

	return true;

    }

    public List<Seat> getSeats() {
	List<Seat> seats = new LinkedList<Seat>();
	if (trip.getId() != null)
	    seats = Factories.services.getSeatsService().findByTrip(
		    trip.getId());
	return seats;
    }

    public List<Application> getApplications() {
	List<Application> applications = new LinkedList<>();
	if (trip.getId() != null)
	    applications = Factories.services.getSeatsService()
		    .findApplicationByTrip(trip.getId());
	return applications;
    }

    public Application getApplicationUser(Long idUser) {
	if (trip.getId() != null)
	    return Factories.services.getApplicationService().findApplication(
		    trip.getId(), idUser);
	return null;
    }

    public String updateTrip(Long idUser) {
	try {
	    Factories.services.getTripsService().update(trip, idUser);
	} catch (EntityNotFoundException e) {
	    return "fracaso";
	}

	return "exito";
    }

    public String cancelTrip(Long idUser) {
	Log.trace("Iniciando cancelación de viaje.");
	try {
	    Factories.services.getTripsService().cancel(trip, idUser);
	} catch (EntityNotFoundException e) {
	    Log.error("No se ha encontrado el viaje.", e);
	    return "fracaso";
	} catch (EntityAlreadyExistsException e) {
	    Log.error("Los asientos ya están actualizados." + e);
	    return "fracaso";
	}
	Log.info("Viaje cancelado.");
	return "exito";
    }

    public String saveTrip(Long idUser) {
	Log.trace("Iniciando salva de viaje.");
	Log.debug("Viaje: " + trip);
	try {
	    Factories.services.getTripsService().save(trip, idUser);
	} catch (EntityAlreadyExistsException e) {
	    Log.error("Ya existe el viaje.", e);
	    return "fracaso";
	}
	Log.info("Viaje guardado con éxito.");
	return "exito";
    }

    public String view(Long idTrip) {
	try {
	    trip = Factories.services.getTripsService().findTripById(idTrip);
	} catch (EntityNotFoundException e) {
	    Log.error("Fracaso view trip", e);
	    return "fracaso";
	}
	return "exito";
    }

    public Trip getTrip() {
	return trip;
    }

    public void setTrip(Trip trip) {
	this.trip = trip;
    }

    /**
     * Si el usuario es primitor, participante o ha pedido plaza
     */
    public boolean isUserRelated(Long idUser) {
	if (isPromoter(idUser))
	    return true;

	if (isUserInSeats(idUser))
	    return true;

	if (isUserInApplications(idUser))
	    return true;

	return false;
    }

    public boolean isUserInSeats(Long idUser) {
	try {
	    Factories.services.getSeatsService().findByUserAndTrip(idUser,
		    trip.getId());
	} catch (EntityNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return true;

    }

    public boolean isUserInApplications(Long idUser) {
	Factories.services.getApplicationService().findApplication(
		trip.getId(), idUser);
	return true;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public void checkTripNotNull() throws IOException {
	actualizar();
	if (null == trip.getId()) {
	    ExternalContext ec = FacesContext.getCurrentInstance()
		    .getExternalContext();
	    ec.redirect(ec.getRequestContextPath() + "/error.xhtml");
	}
    }

    public String acceptApplication(Application application) {

	try {
	    Factories.services.getApplicationService().acceptApplication(
		    application);
	    actualizarTrip();
	} catch (EntityAlreadyExistsException | EntityNotFoundException e) {
	    return "fracaso";
	}

	return "exito";
    }

    private void actualizarTrip() throws EntityNotFoundException {
	trip = Factories.services.getTripsService().findTripById(
		Long.parseLong(id));
    }

    public String cancelApplication(Application application) {
	try {
	    Factories.services.getApplicationService().cancelApplication(
		    application);
	} catch (EntityAlreadyExistsException | EntityNotFoundException e) {
	    return "fracaso";
	}

	return "exito";
    }

    public String cancelSeat(Seat seat) {
	try {
	    Factories.services.getSeatsService().cancelSeat(seat);
	    actualizarTrip();
	} catch (EntityNotFoundException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "ERROR", "El viaje ya pasó");

	    addMessage(message);
	    return "fracaso";
	}

	return "exito";
    }

    public boolean isTripVoid() {
	if (id == null || id.trim().equals("") || trip == null
		|| trip.getId() == null)
	    return true;
	return false;
    }

    public boolean isActive() {
	return trip.getStatus().isOpen();
    }

    private void addMessage(FacesMessage message) {
	FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
