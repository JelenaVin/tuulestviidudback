package ee.valiit.tuulestviidudback.controller.beachweather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeachWeatherReport {
    private String weatherTimeStamp;
    private List<BeachWeather> beachWeathers;

}
