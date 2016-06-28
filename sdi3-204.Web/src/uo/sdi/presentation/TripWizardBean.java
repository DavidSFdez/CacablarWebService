package uo.sdi.presentation;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.AddressPoint;
import uo.sdi.model.Trip;
import uo.sdi.model.Waypoint;

@ManagedBean(name = "wizard")
@RequestScoped
public class TripWizardBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Trip trip = new Trip();
    private String addressD;
    private String cityD;
    private String stateD;
    private String countryD;
    private String zipCodeD;
    private Double latD = 0D;
    private Double lonD = 0D;
    private String addressA;
    private String cityA;
    private String stateA;
    private String countryA;
    private String zipCodeA;
    private Double latA = 0D;
    private Double lonA = 0D;
    private boolean isUpdate = false;

    @ManagedProperty("#{param.updateTrip}")
    private String id;

    public TripWizardBean() {
    }

    public String accept() {

	checkAllRight();

	return "exito";

    }

    private boolean checkAllRight() {
	if (addressA == null || addressD == null || cityA == null
		|| cityD == null || stateA == null || stateD == null
		|| countryA == null || countryD == null || zipCodeA == null
		|| zipCodeD == null) {

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "ERROR", "Rellena todos los campos gañan");

	    addMessage(message);
	    return false;
	}

	if (trip.getDepartureDate() == null || trip.getArrivalDate() == null
		|| trip.getClosingDate() == null) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "ERROR", "Rellena todas fechas gañan");

	    addMessage(message);
	    return false;
	} else if (trip.getDepartureDate().before(trip.getClosingDate())
		|| trip.getArrivalDate().before(trip.getDepartureDate())) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "ERROR", "Fechas introducidas no válidas gañan");

	    addMessage(message);
	    return false;
	}

	if (trip.getEstimatedCost() <= 0D) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "ERROR", "Precio introducido no válido gañan");

	    addMessage(message);
	    return false;
	} else if (trip.getMaxPax() <= 0) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "ERROR", "Asientos disponibles no válido gañan");

	    addMessage(message);
	    return false;
	}

	if (trip.getComments() == null) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "ERROR",
		    "No has introducido comentarios o descripción gañán");

	    addMessage(message);
	    return false;
	}

	return true;

    }

    private void addMessage(FacesMessage message) {
	FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Trip getTrip() {
	return trip;
    }

    public void preloadTripData() {

	int random = new Random().nextInt();

	this.addressD = "direccionSalida" + random;
	this.cityD = "CiudadSalida" + random;
	this.countryD = "PaísSalida" + random;
	this.latD = 0D;
	this.lonD = 0D;
	this.stateD = "ProvinciaSalida" + random;
	this.zipCodeD = "CodigoPostalSalida" + random;

	this.addressA = "direccionLlegada" + random;
	this.cityA = "CiudadLlegada" + random;
	this.countryA = "PaísLlegada" + random;
	this.latA = 0D;
	this.lonA = 0D;
	this.stateA = "ProvinciaLlegada" + random;
	this.zipCodeA = "CodigoPostalLlegada" + random;

	trip.setArrivalDate(new Date());
	trip.setDepartureDate(new Date());
	trip.setClosingDate(new Date());
	trip.setEstimatedCost(10D);
	trip.setComments("Descripción o comentarios" + random);
	trip.setAvailablePax(1);
	trip.setMaxPax(1);

    }

    @PostConstruct
    public void updateTripData() {

	if (id != null) {
	    try {
		this.trip = Factories.services.getTripsService().findTripById(
			Long.parseLong(id));
	    } catch (NumberFormatException e) {

	    }

	    this.addressD = trip.getDeparture().getAddress();
	    this.cityD = trip.getDeparture().getCity();
	    this.countryD = trip.getDeparture().getCountry();
	    this.latD = trip.getDeparture().getWaypoint().getLat();
	    this.lonD = trip.getDeparture().getWaypoint().getLon();
	    this.stateD = trip.getDeparture().getState();
	    this.zipCodeD = trip.getDeparture().getZipCode();

	    this.addressA = trip.getDestination().getAddress();
	    this.cityA = trip.getDestination().getCity();
	    this.countryA = trip.getDestination().getCountry();
	    this.latA = trip.getDestination().getWaypoint().getLat();
	    this.lonA = trip.getDestination().getWaypoint().getLon();
	    this.stateA = trip.getDestination().getState();
	    this.zipCodeA = trip.getDestination().getZipCode();

	    this.isUpdate = true;
	}

    }

    public String modifyTrip(Long idUser) {

	try {

	    if (checkAllRight() == false)
		return "fracaso";

	    AddressPoint departure = new AddressPoint(addressD, cityD, stateD,
		    countryD, zipCodeD, new Waypoint(latD, lonD));
	    AddressPoint destination = new AddressPoint(addressA, cityA,
		    stateA, countryA, zipCodeA, new Waypoint(latA, lonA));

	    trip.setDeparture(departure);
	    trip.setDestination(destination);

	    Factories.services.getTripsService().update(trip, idUser);
	    this.isUpdate = false;

	} catch (EntityNotFoundException e) {
	    return "false";
	}

	return "exito";
    }

    public String save(Long idUser) {
	try {

	    if (!checkAllRight())
		return "fracaso";

	    AddressPoint departure = new AddressPoint(addressD, cityD, stateD,
		    countryD, zipCodeD, new Waypoint(latD, lonD));
	    AddressPoint destination = new AddressPoint(addressA, cityA,
		    stateA, countryA, zipCodeA, new Waypoint(latA, lonA));

	    trip.setAvailablePax(trip.getMaxPax());
	    trip.setDeparture(departure);
	    trip.setDestination(destination);

	    Factories.services.getTripsService().save(trip, idUser);
	} catch (EntityAlreadyExistsException e) {

	    return "fracaso";
	}

	return "exito";
    }

    public Date getToday() {
	return new Date();
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public void setTrip(Trip trip) {
	this.trip = trip;
    }

    public String getAddressD() {
	return addressD;
    }

    public void setAddressD(String addressD) {
	this.addressD = addressD;
    }

    public String getCityD() {
	return cityD;
    }

    public void setCityD(String cityD) {
	this.cityD = cityD;
    }

    public String getStateD() {
	return stateD;
    }

    public void setStateD(String stateD) {
	this.stateD = stateD;
    }

    public String getCountryD() {
	return countryD;
    }

    public void setCountryD(String countryD) {
	this.countryD = countryD;
    }

    public String getZipCodeD() {
	return zipCodeD;
    }

    public void setZipCodeD(String zipCodeD) {
	this.zipCodeD = zipCodeD;
    }

    public Double getLatD() {
	return latD;
    }

    public void setLatD(Double latD) {
	this.latD = latD;
    }

    public Double getLonD() {
	return lonD;
    }

    public void setLonD(Double lonD) {
	this.lonD = lonD;
    }

    public String getAddressA() {
	return addressA;
    }

    public void setAddressA(String addressA) {
	this.addressA = addressA;
    }

    public String getCityA() {
	return cityA;
    }

    public void setCityA(String cityA) {
	this.cityA = cityA;
    }

    public String getStateA() {
	return stateA;
    }

    public void setStateA(String stateA) {
	this.stateA = stateA;
    }

    public String getCountryA() {
	return countryA;
    }

    public void setCountryA(String countryA) {
	this.countryA = countryA;
    }

    public String getZipCodeA() {
	return zipCodeA;
    }

    public void setZipCodeA(String zipCodeA) {
	this.zipCodeA = zipCodeA;
    }

    public Double getLatA() {
	return latA;
    }

    public void setLatA(Double latA) {
	this.latA = latA;
    }

    public Double getLonA() {
	return lonA;
    }

    public void setLonA(Double lonA) {
	this.lonA = lonA;
    }

    public boolean isUpdate() {
	return isUpdate;
    }

    public void setUpdate(boolean isUpdate) {
	this.isUpdate = isUpdate;
    }

}
