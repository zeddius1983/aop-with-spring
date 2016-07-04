package org.aop.weather;

import org.aop.aspects.Log;
import org.aop.aspects.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class WeatherServiceController {

    @Autowired
    private WeatherServiceBean weatherService;

    @Log @Profile
    @RequestMapping("/weather")
    public String getWeather(@RequestParam(required = false) String location) throws IOException {
        return weatherService.getWeather(location);
    }

}