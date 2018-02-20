package com.ifood.controllers;

import com.ifood.models.OpenWeatherMapResponse;
import com.ifood.services.OpenWeatherMapService;
import com.ifood.utils.MathUtils;
import com.ifood.utils.TemperatureConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.DecimalFormat;
import java.time.Instant;
import java.util.Date;

import static jdk.nashorn.internal.objects.NativeMath.round;

@Controller
public class Weather {
    private OpenWeatherMapService openWeatherMapService;

    public Weather(OpenWeatherMapService openWeatherMapService) {
        this.openWeatherMapService = openWeatherMapService;
    }

    @RequestMapping(path = "/weather/{cityName}", method = RequestMethod.GET)
    public String getWeatherByCityNameInPath(@PathVariable("cityName") String cityName, Model model) {
        OpenWeatherMapResponse currentWeather = openWeatherMapService.getWeatherByCityName(cityName);
        if (currentWeather == null) {
            model.addAttribute("cityName", cityName);
            return "notFound";
        }
        //testar se tem todos os atributos que eu quero
        currentWeather.getMain().setTemp(MathUtils.round(TemperatureConverter.kelvinToCelsius(currentWeather.getMain().getTemp()), 1));
        currentWeather.getMain().setTemp_min(TemperatureConverter.kelvinToCelsius(currentWeather.getMain().getTemp_min()));
        currentWeather.getMain().setTemp_max(TemperatureConverter.kelvinToCelsius(currentWeather.getMain().getTemp_max()));
        model.addAttribute("currentWeather", currentWeather);
        model.addAttribute("date", Date.from( Instant.ofEpochSecond(currentWeather.getDt()) ));
        return "weather";
    }
}
