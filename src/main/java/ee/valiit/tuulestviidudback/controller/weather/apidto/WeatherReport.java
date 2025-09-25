package ee.valiit.tuulestviidudback.controller.weather.apidto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherReport {
    @JsonProperty("current")
    private CurrentWeather current;
}
