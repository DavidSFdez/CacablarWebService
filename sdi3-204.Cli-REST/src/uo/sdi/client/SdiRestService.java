package uo.sdi.client;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uo.sdi.model.Trip;

@Path("/sdirs")
public interface SdiRestService {

    @Path("/trips/all")
    @GET
    @Produces({ APPLICATION_JSON, APPLICATION_XML })
    List<Trip> tripsAll();

    @Path("/trips")
    @GET
    @Produces({ APPLICATION_JSON, APPLICATION_XML })
    List<Trip> tripsPromoted();

    @Path("/seats/{idTrip}")
    @GET
    @Produces({ APPLICATION_JSON, APPLICATION_XML })
    List<uo.sdi.model.Application> getApplicationsByTrip(
	    @PathParam("idTrip") Long idTrip);
    
    @Path("/seats/acept")
    @PUT
    @Consumes({ APPLICATION_JSON, APPLICATION_XML })
    void aceptApplications(uo.sdi.model.Application application);

}
