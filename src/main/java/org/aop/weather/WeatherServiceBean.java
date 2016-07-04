package org.aop.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aop.aspects.Log;
import org.aop.aspects.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

/**
 * Weather forecast service default implementation.
 *
 * @author Alexander Valyugin
 */
@Service
public class WeatherServiceBean implements WeatherService {

    private static final String[] WEATHER = {"Clear", "Cloudy", "Rain", "Snow", "Fog", "Shower"};

    private Random rnd = new Random();

    @Override @Log
    public String getWeather(String location) {
        if (location == null) try {
            location = determineCurrentLocation();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return WEATHER[rnd.nextInt(WEATHER.length)] + " in " + location;
    }

    @Log(level = Log.Level.DEBUG) @Profile
    private String determineCurrentLocation() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String locationJson = restTemplate.getForObject("http://freegeoip.net/json/", String.class);
        ObjectMapper mapper = new ObjectMapper();
        return (String) mapper.readValue(locationJson, HashMap.class).get("city");
    }

}
