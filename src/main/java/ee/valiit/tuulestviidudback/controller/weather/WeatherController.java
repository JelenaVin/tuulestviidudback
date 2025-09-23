package ee.valiit.tuulestviidudback.controller.weather;

import ee.valiit.tuulestviidudback.controller.weather.apidto.WeatherResponse;
import ee.valiit.tuulestviidudback.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/weather-lat-lng")
    public WeatherResponse getWeatherInfo(@RequestParam String lat, @RequestParam String lng) {
        return weatherService.getWeatherInfo(lat,lng);
    }

    @GetMapping("/weather-all-beaches")
    public void updateAllBeachesWeatherInfo() {
        weatherService.updateAllBeachesWeatherInfo();
    }

    @GetMapping("/weather-all-beaches/subscription")
    public void updateSubscriptionAllBeachesWeatherInfo(@RequestParam Integer userId) {

    }
}
