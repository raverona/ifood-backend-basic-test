package com.ifood.utils;

import com.ifood.models.openWeatherMapResponse.OpenWeatherMapResponse;

import java.util.function.DoubleFunction;

public class TemperatureConverter {
    public static DoubleFunction<Double> kelvinToCelsius  = (kelvin) -> kelvin - 273.15;

    public static double convertWeatherTemperature (double temperature, DoubleFunction<Double> doubleFunction) {
        return MathUtils.round(doubleFunction.apply(temperature), 1);
    }
}
