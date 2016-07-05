package org.aop.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class WeatherServiceController {

    @Autowired
    private WeatherService weatherService;

    @RequestMapping("/weather")
    public String getWeather(@RequestParam(required = false) String location) throws IOException {
        return weatherService.getWeather(location);
    }

}