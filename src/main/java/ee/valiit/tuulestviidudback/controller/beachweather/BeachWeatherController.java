package ee.valiit.tuulestviidudback.controller.beachweather;

import ee.valiit.tuulestviidudback.controller.beachweather.dto.BeachWeatherReport;
import ee.valiit.tuulestviidudback.service.BeachWeatherService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BeachWeatherController {
    private final BeachWeatherService beachWeatherService;

        @GetMapping("/beach/weather/free-report")
    @Operation(summary = "")
    public BeachWeatherReport getFreeBeachWeatherReport() {
        return beachWeatherService.getFreeBeachWeatherReport();
    }

    @GetMapping("/beach/weather/paid-report")
    @Operation(summary = "")
    public BeachWeatherReport getPaidBeachWeatherReport() {
        return beachWeatherService.getPaidBeachWeatherReport();
    }

}
