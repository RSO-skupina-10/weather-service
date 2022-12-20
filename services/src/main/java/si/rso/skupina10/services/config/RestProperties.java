package si.rso.skupina10.services.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ConfigBundle("rest-properties")
@ApplicationScoped
public class RestProperties {
    @ConfigValue("weatherapi.access-key")
    private String weatherApiAccessKey;


    @ConfigValue("weatherapi.secret-key")
    private String weatherApiSecretKey;


    public String getWeatherApiAccessKey() {
        return weatherApiAccessKey;
    }

    public void setWeatherApiAccessKey(String weatherApiAccessKey) {
        this.weatherApiAccessKey = weatherApiAccessKey;
    }

    public String getWeatherApiSecretKey() {
        return weatherApiSecretKey;
    }

    public void setWeatherApiSecretKey(String weatherApiSecretKey) {
        this.weatherApiSecretKey = weatherApiSecretKey;
    }
}
