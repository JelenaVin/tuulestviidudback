package ee.valiit.tuulestviidudback.service;

import ee.valiit.tuulestviidudback.controller.weather.apidto.WeatherResponse;
import ee.valiit.tuulestviidudback.persistance.beach.Beach;
import ee.valiit.tuulestviidudback.persistance.beach.BeachRepository;
import ee.valiit.tuulestviidudback.persistance.weather.Weather;
import ee.valiit.tuulestviidudback.persistance.weather.WeatherMapper;
import ee.valiit.tuulestviidudback.persistance.weather.WeatherRepository;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {

    public static final String STANDARD_PARAMETERS = "&current=wind_speed_10m,wind_direction_10m,wind_gusts_10m,temperature_2m,precipitation&wind_speed_unit=ms";
    private final WebClient webClient = WebClient.builder().baseUrl("https://api.open-meteo.com").build();
    private final WeatherMapper weatherMapper;
    private final WeatherRepository weatherRepository;
    private final BeachRepository beachRepository;

    public WeatherResponse getWeatherInfo(String lat, String lng) {
        String uri = "/v1/forecast?latitude=" + lat + "&longitude=" + lng + STANDARD_PARAMETERS;
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(WeatherResponse.class)
                .block(); // blocking for simplicity
    }

    public void updateBeachesWeatherInfo() {

        String subscriptionType = "F";

        List<Beach> beaches = beachRepository.findBeachesBy("A");
        for (Beach beach : beaches) {
            String lat = beach.getLat().toString();
            String lng = beach.getLng().toString();
            WeatherResponse weatherResponse = getWeatherInfo(lat, lng);
            Weather weather = weatherMapper.toWeather(weatherResponse);
            weather.setBeach(beach);
            weather.setTimestamp(Instant.now());
            String surfStatus = calculateSurfStatus(beach, weather);
            weather.setSurfStatus(surfStatus);
            weather.setType(subscriptionType);
            weatherRepository.save(weather);


            if (subscriptionType.equals("F")) {
                beach.setSurfStatus(surfStatus);
                beach.setLastUpdate(Instant.now());
                beachRepository.save(beach);
            }
        }


    }

    private String calculateSurfStatus(Beach beach, Weather weather) {
        //todo: vaja mõelda GREEN, AMBER, RED loogika
        return "GREEN";
    }


}
