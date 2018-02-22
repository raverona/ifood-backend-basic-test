package com.ifood.controllers;

import com.ifood.models.openWeatherMapResponse.OpenWeatherMapResponse;
import com.ifood.services.OpenWeatherMapService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    @HystrixCommand(fallbackMethod = "googleFallback")
    @RequestMapping(path = "/weather/{cityName}", method = RequestMethod.GET)
    public String getWeatherByCityNameInPath(@PathVariable("cityName") String cityName, Model model) {
        logger.info("Fetching weather data for city: " + cityName.toLowerCase());
        OpenWeatherMapResponse currentWeather = openWeatherMapService.getWeatherByCityName(cityName.toLowerCase());

        model.addAttribute("currentWeather", currentWeather);
        model.addAttribute("date", Date.from(Instant.ofEpochSecond(currentWeather.getDt())));
        return "weather";
    }

    public String googleFallback(String cityName, Model model) {
        logger.error("Failed on getting weather information for city: " + cityName.toLowerCase());
        return "redirect:https://www.google.com.br/search?hl=en&q=weather+wells";
    }
}
