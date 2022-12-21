package si.rso.skupina10.api.v1.resources;

import si.rso.skupina10.clients.WeatherClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/weather")
public class WeatherResource {

    @Inject
    private WeatherClient weatherClient;


    @GET
    @Path("{city}")
    public Response getPriceFactor(@PathParam("city") String city) {
        int resp = weatherClient.getRate(city);

        return Response.ok(resp).build();
    }
}
