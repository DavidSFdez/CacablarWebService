package uo.sdi.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uo.sdi.model.Trip;
import uo.sdi.model.User;

@Path("/cacablarRS")
public interface ServiceREST {

    @GET
    @Path("/login")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    boolean login(String usuario, String password, HttpServletRequest req);

    @GET
    // Produce Trips en xml o json
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    List<Trip> listPromotedActiveTrips(HttpServletRequest req);

    @GET
    @Path("{idTrip}")
    // Produce Applications en xml o json
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    List<uo.sdi.model.Application> selectTrip(Long idTrip);

    @POST
    // Consume Applications en xml o json
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    void acceptSeat(uo.sdi.model.Application application);

    @GET
    @Path("/test")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    Trip[] testREST();

}
