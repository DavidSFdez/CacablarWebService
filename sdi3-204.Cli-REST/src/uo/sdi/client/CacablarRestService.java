package uo.sdi.client;

import java.util.List;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("/cacablarRS")
public interface CacablarRestService {

    
    @GET
    // Produce Trips en xml o json
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    boolean login(String usuario, String password);
    
    @GET
    // Produce Trips en xml o json
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    List<Trip> listPromotedActiveTrips();
    
    @GET
    @Path("{idTrip}")
    // Produce Applications en xml o json
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    List<uo.sdi.client.Application> selectTrip(Long idTrip);
    
    @POST
    // Consume Applications en xml o json
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    void acceptSeat(uo.sdi.client.Application application);

   
}
