package com.ifood.controllers;

import com.ifood.models.openWeatherMapResponse.OpenWeatherMapResponse;
import com.ifood.models.ServiceStatus;
import com.ifood.services.OpenWeatherMapService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class About {
    private static ServiceStatus serviceStatus;

    private OpenWeatherMapService openWeatherMapService;

    public About(OpenWeatherMapService openWeatherMapService) {
        this.openWeatherMapService = openWeatherMapService;
    }

    @RequestMapping(path = "/about", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ServiceStatus serviceStatus() {
        if (serviceStatus == null) return new ServiceStatus(new Date(), "Status not available, please try again in a few seconds.");
        return serviceStatus;
    }

    @Scheduled(fixedRateString = "${openweathermap.apitestinterval.inmilliseconds}", initialDelay = 5000)
    private void renewServiceStatus() {
        serviceStatus = checkStatus();
    }

    private ServiceStatus checkStatus() {
        OpenWeatherMapResponse currentWeather = openWeatherMapService.getWeatherByCityName("wells");
        if (currentWeather == null)
            return serviceStatus = new ServiceStatus(new Date(), "OpenWeatherMap API is not responding, dark times may be ahead.", new Date(System.currentTimeMillis()+5*60*1000));
        else
            return serviceStatus = new ServiceStatus(new Date(), "OpenWeatherMap API seems to be available, everything should work fine.", new Date(System.currentTimeMillis()+5*60*1000));
    }
}
