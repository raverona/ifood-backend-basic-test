package com.ifood.services;

import com.ifood.models.openWeatherMapResponse.OpenWeatherMapResponse;
import com.ifood.utils.TemperatureConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenWeatherMapService {
    private final static Logger logger = Logger.getLogger(OpenWeatherMapService.class);
    private RestTemplate restTemplate;

    @Value("${openweathermap.apikey}")
    String APIkey;

    public OpenWeatherMapService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(value = "cityWeather", key = "#cityName", condition = "#cityName != 'wells'", unless = "#result == null")
    public OpenWeatherMapResponse getWeatherByCityName(String cityName) {
        OpenWeatherMapResponse openWeatherMapResponse;
        try {
            logger.info("Getting new weather information for city: " + cityName.toLowerCase());
            openWeatherMapResponse = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?q=" + cityName.toLowerCase() + "&appid=" + APIkey, OpenWeatherMapResponse.class);
            TemperatureConverter.convertWeatherTemperature(openWeatherMapResponse, TemperatureConverter.kelvinToCelsius);
        } catch (Exception e) {
            return null;
        }
        return openWeatherMapResponse;
    }
}
