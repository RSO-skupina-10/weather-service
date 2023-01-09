package si.rso.skupina10.api.v1.resources;

import si.rso.skupina10.services.config.RestProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@ApplicationScoped
@Path("/demo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DemoResource {

    private Logger log = Logger.getLogger(DemoResource.class.getName());

    @Inject
    private RestProperties restProperties;

    @GET
    @Path("break")
    public Response hideKey(){
        restProperties.setTimeout(true);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("unbreak")
    public Response unhideKey(){
        restProperties.setTimeout(false);
        return Response.status(Response.Status.OK).build();
    }
}
