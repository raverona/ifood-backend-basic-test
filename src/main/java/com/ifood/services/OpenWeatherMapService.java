package com.ifood.services;

import com.ifood.models.openWeatherMapResponse.OpenWeatherMapResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenWeatherMapService {
    private RestTemplate restTemplate;

    @Value("${openweathermap.apikey}")
    String APIkey;

    public OpenWeatherMapService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(value = "cityWeather", key = "#cityName", condition = "#cityName != 'Wells'")
    public OpenWeatherMapResponse getWeatherByCityName(String cityName) {
        OpenWeatherMapResponse openWeatherMapResponse;
        try {
            openWeatherMapResponse = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + APIkey, OpenWeatherMapResponse.class);
        } catch (Exception e) {
            return null;
        }
        return openWeatherMapResponse;
    }
}
