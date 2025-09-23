package ee.valiit.tuulestviidudback.controller.weather.apidto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherResponse {
    @JsonProperty("current")
    private CurrentWeather current;
}
