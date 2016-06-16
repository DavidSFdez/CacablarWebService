package uo.sdi.presentation;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import alb.util.log.Log;
import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;

@ManagedBean(name = "trips")
@ViewScoped
public class TripsBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Trip> trips;

    private List<Trip> tripsToCancel;

    private boolean aux = false;

    public List<Trip> getTrips() {
	return trips;
    }

    public void setTrips(List<Trip> trips) {
	this.trips = trips;
    }

    public List<Trip> getTripsToCancel() {
	return tripsToCancel;
    }

    public void setTripsToCancel(List<Trip> tripsToCancel) {
	this.tripsToCancel = tripsToCancel;
    }

    public boolean isAux() {
	return aux;
    }

    public void setAux(boolean aux) {
	this.aux = aux;
    }

    public TripsBean() {
	trips = new LinkedList<>();
	tripsToCancel = new LinkedList<>();
    }

    public boolean value(Trip trip) {

	return tripsToCancel.contains(trip);
    }

    public String cancelTrips(long idUser) {
	Log.trace("Iniciando cancelación del viaje.");
	Log.debug("Id de usuario cancenando: [" + idUser + "].");
	try {
	    if (tripsToCancel != null && tripsToCancel.size() != 0)
		for (Trip trip : tripsToCancel)
		    Factories.services.createTripsService()
			    .cancel(trip, idUser);
	    tripsToCancel = new LinkedList<>();
	} catch (EntityNotFoundException e) {
	    Log.error("No se han contrado los viajes a cancelar.");
	    return "fracaso";
	} catch (EntityAlreadyExistsException e) {
	    Log.error("Intento de inserción de entidad repetida en el sistema.");
	    return "fracaso";
	}
	Log.info("Viajes cancelados con exito.");
	return "exito";
    }

    public List<Trip> getListActive() {
	Log.info("Buscando viajes activos.");
	List<Trip> trips = null;
	trips = Factories.services.createTripsService().listActive();
	this.trips = trips;
	Log.debug("Viajes: " + this.trips);
	return trips;
    }

    public List<Trip> listRelated(Long idUser) {
	Log.info("Buscando viajes relacionados.");
	List<Trip> trips = new LinkedList<Trip>();
	try {
	    trips = Factories.services.createTripsService().listRelated(idUser);
	} catch (Exception e) {
	    Log.error("ha ocurrido un error.", e);
	}
	this.trips = trips;
	Log.debug("Viajes: " + this.trips);
	return trips;
    }

    public List<Trip> getListActiveToUser(Long idUser) {
	Log.info("Buscando viajes activos a un usuario.");
	List<Trip> trips = new LinkedList<Trip>();
	if (idUser == null) {
	    this.trips = trips;
	    return getListActive();
	}
	try {
	    trips = Factories.services.createTripsService().listActiveToUser(
		    idUser);
	} catch (Exception e) {
	    Log.error("ha ocurrido un error.", e);
	}

	this.trips = trips;
	Log.debug("Viajes: " + this.trips);
	return trips;
    }

}
