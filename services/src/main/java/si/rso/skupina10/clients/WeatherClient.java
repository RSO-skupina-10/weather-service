package si.rso.skupina10.clients;

import si.rso.skupina10.services.config.RestProperties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
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
    }


}
