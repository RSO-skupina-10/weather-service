package si.rso.skupina10.api.v1.resources;

import org.eclipse.microprofile.metrics.annotation.Timed;
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
    @Path("{latlng}")
    @Timed (name = "weather_api_call")
    public Response getPriceFactor(@PathParam("latlng") String latlng) {
        int resp = weatherClient.getRate(latlng);

        return Response.ok(resp).build();
    }
}
