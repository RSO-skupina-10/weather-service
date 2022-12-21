package si.rso.skupina10.clients;

import si.rso.skupina10.services.config.RestProperties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@ApplicationScoped
public class WeatherClient {
    private Client httpClient;
    private String baseUrl = "https://weatherapi-com.p.rapidapi.com";

    @Inject
    private RestProperties restProperties;

    private Logger log = Logger.getLogger(WeatherClient.class.getName());

    @PostConstruct
    private void init(){
        log.info("Inicializacija zrna " + WeatherClient.class.getName());

        httpClient = ClientBuilder.newClient();
        //httpClient.getProtocolHandlers().remove(WWWAuthenticationProtocolHandler.NAME);
    }

    public int getRate(String city) {
        Response response = null;
        try {
            response = httpClient.target(baseUrl + "/current.json?q=" + city)
                    .request(MediaType.APPLICATION_JSON)
                    .header("X-RapidAPI-Key", restProperties.getWeatherApiSecretKey())
                    .header("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                    .get();
        } catch (Exception e) {
            log.severe(e.getMessage());
        }
        if(response.getStatus() > 199 && response.getStatus() < 300) {
            log.info("Got response");
            System.out.println(response.readEntity(String.class));
        } else {
            log.info("Request failed " + response.getStatus());
        }
        return 1;
    }


}
