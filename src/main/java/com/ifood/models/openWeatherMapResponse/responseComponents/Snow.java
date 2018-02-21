package com.ifood.models.openWeatherMapResponse.responseComponents;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Snow {
    @JsonProperty("3h")
    private float threeH;

    public float getThreeH() {
        return threeH;
    }

    public void setThreeH(float threeH) {
        this.threeH = threeH;
    }

    @Override
    public String toString() {
        return "Snow{" +
                "threeH=" + threeH +
                '}';
    }
}
