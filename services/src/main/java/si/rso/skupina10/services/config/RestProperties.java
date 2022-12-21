package si.rso.skupina10.services.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ConfigBundle("app-properties")
@ApplicationScoped
public class RestProperties {


    @ConfigValue("weatherapi.secret-key")
    private String weatherApiSecretKey;


    public String getWeatherApiSecretKey() {
        return weatherApiSecretKey;
    }

    public void setWeatherApiSecretKey(String weatherApiSecretKey) {
        this.weatherApiSecretKey = weatherApiSecretKey;
    }
}
