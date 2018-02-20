package com.ifood.controllers;

import com.ifood.models.OpenWeatherMapResponse;
import com.ifood.models.ServiceStatus;
import com.ifood.services.OpenWeatherMapService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class About {
    @Value("${openweathermap.apitestinterval.inminutes}")
    private int openWeatherMapApiTestIntervalInMinutes;
    private static ServiceStatus serviceStatus;

    private OpenWeatherMapService openWeatherMapService;

    public About(OpenWeatherMapService openWeatherMapService) {
        this.openWeatherMapService = openWeatherMapService;
    }

    @RequestMapping(path = "/about", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ServiceStatus about() {
        if (serviceStatus == null) {
           serviceStatus = checkStatus();
        }
        else {
            long diff = (new Date().getTime() - serviceStatus.getLastCheck().getTime()) / (60 * 1000) % 60;
            if (diff < openWeatherMapApiTestIntervalInMinutes) {
                serviceStatus.setNextCheck("It's been " + diff + " minute(s) since OpenWeatherMap API availability was last checked, check again in " + (openWeatherMapApiTestIntervalInMinutes - diff) + " minute(s)");
            } else {
                serviceStatus = checkStatus();
            }
        }
        return serviceStatus;
    }

    private ServiceStatus checkStatus() {
        OpenWeatherMapResponse currentWeather = openWeatherMapService.getWeatherByCityName("London");
        if (currentWeather == null)
            return serviceStatus = new ServiceStatus(new Date(), "OpenWeatherMap API is not responding, dark times may be ahead.");
        else
            return serviceStatus = new ServiceStatus(new Date(), "OpenWeatherMap API seems to be available, everything should work fine.");
    }
}
