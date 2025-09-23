package ee.valiit.tuulestviidudback.controller.weather.apidto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class CurrentWeather {

    @JsonProperty("wind_speed_10m")
    private double windSpeed10m;

    @JsonProperty("wind_direction_10m")
    private int windDirection10m;

    @JsonProperty("wind_gusts_10m")
    private double windGusts10m;

    @JsonProperty("temperature_2m")
    private double temperature2m;

    private double precipitation;
}
