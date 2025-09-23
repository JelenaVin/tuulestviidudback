package ee.valiit.tuulestviidudback.service;

import ee.valiit.tuulestviidudback.controller.weather.apidto.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class WeatherService {

    public static final String STANDARD_PARAMETERS = "&current=wind_speed_10m,wind_direction_10m,wind_gusts_10m,temperature_2m,precipitation&wind_speed_unit=ms";
    private final WebClient webClient = WebClient.builder().baseUrl("https://api.open-meteo.com").build();

    public WeatherResponse getWeatherInfo(String lat, String lng) {
        String uri = "/v1/forecast?latitude=" + lat + "&longitude=" + lng + STANDARD_PARAMETERS;
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(WeatherResponse.class)
                .block(); // blocking for simplicity
    }

    public void updateAllBeachesWeatherInfo() {

    }
}
