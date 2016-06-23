package uo.sdi.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import uo.sdi.model.Trip;

@Path("/sdirs")
public interface RestService {

    @Path("/trips/all")
    @GET
    @Produces({ APPLICATION_JSON, APPLICATION_XML })
    List<Trip> tripsAll(@Context HttpServletRequest request);
    
    @Path("/trips")
    @GET
    @Produces({ APPLICATION_JSON, APPLICATION_XML })
    List<Trip> tripsPromoted(@Context HttpServletRequest request);
    
}
