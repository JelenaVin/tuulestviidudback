package ee.valiit.tuulestviidudback.controller.weather;

import ee.valiit.tuulestviidudback.controller.weather.apidto.WeatherResponse;
import ee.valiit.tuulestviidudback.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @PostMapping("/weather-update")
    @Operation(summary = "Uue ilmainfo lisamine")
    public void updateBeachesWeatherInfo() {
        weatherService.updateBeachesWeatherInfo();
    }


    @GetMapping("/weather-lat-lng")
    public WeatherResponse getWeatherInfo(@RequestParam String lat, @RequestParam String lng) {
        return weatherService.getWeatherInfo(lat, lng);
    }



    @GetMapping("/subscription-weather-update")
    public void updateSubscriptionBeachesWeatherInfo(@RequestParam Integer userId) {

    }
}
