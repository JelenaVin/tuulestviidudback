package ee.valiit.tuulestviidudback.controller.weather;

import ee.valiit.tuulestviidudback.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @PutMapping("/free-weather-update")
    @Operation(summary = "Ilmainfo tavakiendile")
    public void updateFreeWeatherInfo() {
        weatherService.updateFreeWeatherInfo();
    }

    @PutMapping("/paid-weather-update")
    @Operation (summary = "Ilmainfo subscriptioniga kliendile")
    public void updatePaidWeatherInfo() {
        weatherService.updatePaidWeatherInfo();
    }
}
