package org.aop.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.aop.aspects.Log;
import org.springframework.beans.factory.annotation.Configurable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Configurable
public class GeoLocation {

    private String city;

    @Log(level = Log.Level.DEBUG)
    public String getCity() {
        return city;
    }

}
