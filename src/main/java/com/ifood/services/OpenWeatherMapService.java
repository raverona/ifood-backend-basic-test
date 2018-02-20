package com.ifood.services;

import com.ifood.models.OpenWeatherMapResponse;
import org.springframework.beans.factory.annotation.Value;
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

    public OpenWeatherMapResponse getWeatherByCityName(String cityName) {
        OpenWeatherMapResponse openWeatherMapResponse;
        try {
            openWeatherMapResponse = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + APIkey, OpenWeatherMapResponse.class);
        } catch (Exception e) {
            return null;
        }
        return openWeatherMapResponse;
    }

    public OpenWeatherMapResponse getWeatherByCityId(String cityId) {
        return restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?id=" + cityId + "&appid=" + APIkey, OpenWeatherMapResponse.class);
    }
}
