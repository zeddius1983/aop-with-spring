package org.aop.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aop.aspects.Log;
import org.aop.aspects.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

/**
 * Weather forecast service default implementation.
 *
 * @author Alexander Valyugin
 */
@Service
public class WeatherServiceBean implements WeatherService {

    @Override @Log
    public String getWeather(Date date, String location) {
        if (date == null) date = new Date();
        if (location == null) try {
            location = determineCurrentLocation();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return "Weather for " + date + " in " + location;
    }

    @Log(level = Log.Level.DEBUG) @Profile
    private String determineCurrentLocation() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String locationJson = restTemplate.getForObject("http://freegeoip.net/json/", String.class);
        ObjectMapper mapper = new ObjectMapper();
        return (String) mapper.readValue(locationJson, HashMap.class).get("city");
    }

}
