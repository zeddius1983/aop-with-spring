package org.aop.weather;

import java.util.Date;

/**
 * This service provides a weather forecast.
 *
 * @author Alexander Valyugin
 */
public interface WeatherService {

    String getWeather(Date date, String location);

}
