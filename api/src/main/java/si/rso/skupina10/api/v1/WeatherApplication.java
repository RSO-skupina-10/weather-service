package si.rso.skupina10.api.v1;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@OpenAPIDefinition(info = @Info(title="Weather API", version="v1",
        contact = @Contact(email="aljkle@rso.si"),
        license = @License(name="dev"), description = "API for getting weather info."),
        servers = @Server(url = "http://localhost:8082/"))
@ApplicationPath("/v1")
public class WeatherApplication extends Application {
}