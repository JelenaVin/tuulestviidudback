package ee.valiit.tuulestviidudback.controller.weather;

import ee.valiit.tuulestviidudback.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;


    @PutMapping("/free-weather-update")
    @Operation(summary = "Uue ilmainfo lisamine")
    public void updateFreeWeatherInfo() {
        weatherService.updateFreeWeatherInfo();
    }

    @PutMapping("/paid-weather-update")
    public void updatePaidWeatherInfo(@RequestParam Integer userId) {
        weatherService.updatePaidWeatherInfo(userId);
    }
}
