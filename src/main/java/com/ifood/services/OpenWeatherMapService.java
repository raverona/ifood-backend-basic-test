package com.ifood.services;

import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OpenWeatherMapService {
    private OWM owm;

    public OpenWeatherMapService(@Value("${openweathermap.apikey}") String APIkey) {
        owm = new OWM(APIkey);
    }

    public CurrentWeather getWeatherByCityName(String cityName) {
        CurrentWeather currentWeather = null;
        try {
            currentWeather = owm.currentWeatherByCityName(cityName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentWeather;
    }

    public CurrentWeather getWeatherByCityId(int cityId) {
        CurrentWeather currentWeather = null;
        try {
            currentWeather = owm.currentWeatherByCityId(cityId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentWeather;
    }
}
