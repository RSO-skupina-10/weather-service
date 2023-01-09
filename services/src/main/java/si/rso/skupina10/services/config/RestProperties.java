package si.rso.skupina10.services.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ConfigBundle("app-properties")
@ApplicationScoped
public class RestProperties {


    @ConfigValue("weatherapi.secret-key")
    private String weatherApiSecretKey;

    @ConfigValue("weatherapi.timeout")
    private Boolean timeout;

    public Boolean getTimeout() { return this.timeout;}

    public void setTimeout(Boolean hideKey) {this.timeout = hideKey;}

    public String getWeatherApiSecretKey() {
            return weatherApiSecretKey;
    }

    public void setWeatherApiSecretKey(String weatherApiSecretKey) {
        this.weatherApiSecretKey = weatherApiSecretKey;
    }
}
