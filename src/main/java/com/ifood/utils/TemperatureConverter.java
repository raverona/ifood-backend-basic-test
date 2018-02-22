package com.ifood.utils;

import com.ifood.models.openWeatherMapResponse.OpenWeatherMapResponse;

import java.util.function.DoubleFunction;

public class TemperatureConverter {
    public static DoubleFunction<Double> kelvinToCelsius  = (kelvin) -> kelvin - 273.15;

    public static void convertWeatherTemperature (OpenWeatherMapResponse openWeatherMapResponse, DoubleFunction<Double> doubleFunction) {
        openWeatherMapResponse.getMain().setTemp(MathUtils.round(doubleFunction.apply(openWeatherMapResponse.getMain().getTemp()), 1));
        openWeatherMapResponse.getMain().setTemp_min(MathUtils.round(doubleFunction.apply(openWeatherMapResponse.getMain().getTemp_min()), 1));
        openWeatherMapResponse.getMain().setTemp_max(MathUtils.round(doubleFunction.apply(openWeatherMapResponse.getMain().getTemp_max()), 1));
    }
}
