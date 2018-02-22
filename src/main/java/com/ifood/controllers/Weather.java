package com.ifood.controllers;

import com.ifood.models.openWeatherMapResponse.OpenWeatherMapResponse;
import com.ifood.services.OpenWeatherMapService;
import com.ifood.utils.MathUtils;
import com.ifood.utils.TemperatureConverter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.Instant;
import java.util.Date;

@Controller
public class Weather {
    private final static Logger logger = Logger.getLogger(Weather.class);
    private OpenWeatherMapService openWeatherMapService;

    public Weather(OpenWeatherMapService openWeatherMapService) {
        this.openWeatherMapService = openWeatherMapService;
    }

    @RequestMapping(path = "/weather/{cityName}", method = RequestMethod.GET)
    public String getWeatherByCityNameInPath(@PathVariable("cityName") String cityName, Model model) {
        logger.info("Fetching weather data for city: " + cityName.toLowerCase());
        OpenWeatherMapResponse currentWeather = openWeatherMapService.getWeatherByCityName(cityName.toLowerCase());

        if (currentWeather == null) {
            model.addAttribute("cityName", cityName);
            return "notFound";
        }

        model.addAttribute("currentWeather", currentWeather);
        model.addAttribute("date", Date.from(Instant.ofEpochSecond(currentWeather.getDt())));
        return "weather";
    }
}
