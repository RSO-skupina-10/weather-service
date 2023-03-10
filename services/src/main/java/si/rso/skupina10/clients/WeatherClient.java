package si.rso.skupina10.clients;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import si.rso.skupina10.services.config.RestProperties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.*;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.StringReader;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
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

    @Timeout(value = 5, unit = ChronoUnit.SECONDS)
    @Fallback(fallbackMethod = "getRateFallback")
    public Integer getRate(String latlng) {
        try{
            int sleepTime = restProperties.getTimeout() ? 10:0;
            TimeUnit.SECONDS.sleep(sleepTime);
        } catch( Exception e){
            log.severe("Sleep exception");
        }
        Response response = null;
        try {
            response = httpClient.target(baseUrl + "/current.json?q=" + latlng)
                    .request(MediaType.APPLICATION_JSON)
                    .header("X-RapidAPI-Key", restProperties.getWeatherApiSecretKey())
                    .header("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                    .get();
        } catch (Exception e) {
            log.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
        if(response != null && response.getStatus() > 199 && response.getStatus() < 300) {
            //System.out.println(response);
            log.info("Got response");
            String jsonString = response.readEntity(String.class);
            //System.out.println(jsonString);
            JsonReader reader = Json.createReader(new StringReader(jsonString));
            JsonObject jsonObject = reader.readObject();
            JsonObject current = jsonObject.getJsonObject("current");
            //System.out.println(current);
            JsonNumber temp = current.getJsonNumber("temp_c");
            JsonNumber precip_mm = current.getJsonNumber("precip_mm");
//            System.out.println(temp);
//            System.out.println(precip_mm);
            float t = (float) temp.doubleValue();
            float p = (float) precip_mm.doubleValue();
            if (t < 15) {
                if (p > 1) {
                    return 3;
                }
                return 2;
            } else if (t > 30) {
                if (p > 1) {
                    return 3;
                }
                return 2;
            }
            if (p > 1) {
                return 2;
            }
            return 1;
        } else if(response != null){
            log.info("Request failed " + response.getStatus());
        } else {
            log.info("Request failed ");
        }
        return null;
    }

    public Integer getRateFallback(String latlng){
        log.info("Fallback method");
        return 0;
    }

}
