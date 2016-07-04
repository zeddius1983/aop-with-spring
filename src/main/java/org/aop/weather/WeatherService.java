package org.aop.weather;

/**
 * This service provides a weather forecast.
 *
 * @author Alexander Valyugin
 */
public interface WeatherService {

    /**
     * Gets the current weather for the given location.
     *
     * @param location the city.
     * @return current weather conditions.
     */
    String getWeather(String location);

}
