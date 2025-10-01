package ee.valiit.tuulestviidudback.service;

import ee.valiit.tuulestviidudback.Status;
import ee.valiit.tuulestviidudback.controller.weather.apidto.WeatherReport;
import ee.valiit.tuulestviidudback.persistance.beach.Beach;
import ee.valiit.tuulestviidudback.persistance.beach.BeachRepository;
import ee.valiit.tuulestviidudback.persistance.weatherinfo.WeatherInfo;
import ee.valiit.tuulestviidudback.persistance.weatherinfo.WeatherInfoMapper;
import ee.valiit.tuulestviidudback.persistance.weatherinfo.WeatherInfoRepository;
import ee.valiit.tuulestviidudback.util.TimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {

    public static final String STANDARD_PARAMETERS = "&current=wind_speed_10m,wind_direction_10m,wind_gusts_10m,temperature_2m,precipitation&wind_speed_unit=ms";
    public static final String SUBSCRIPTION_TYPE_FREE = "F";
    public static final String SUBSCRIPTION_TYPE_PAID = "P";
    private final WebClient webClient = WebClient.builder().baseUrl("https://api.open-meteo.com").build();
    private final WeatherInfoMapper weatherInfoMapper;
    private final WeatherInfoRepository weatherInfoRepository;
    private final BeachRepository beachRepository;
    private final SurfStatusCalculator surfStatusCalculator;


    public void updateFreeWeatherInfo() {
        List<Beach> beaches = beachRepository.findBeachesBy(Status.ACTIVE.getCode());
        Instant estonianTimeNow = TimeUtil.getEstonianTimeNow();
        for (Beach beach : beaches) {
            WeatherReport weatherReport = getApiWeatherReport(beach.getLat(), beach.getLng());
            WeatherInfo weatherInfo = createAndSaveWeatherInfo(beach, weatherReport, SUBSCRIPTION_TYPE_FREE, estonianTimeNow);
            updateBeachSurfStatus(beach, weatherInfo);
        }
    }

    public void updatePaidWeatherInfo() {
        List<Beach> beaches = beachRepository.findBeachesBy(Status.ACTIVE.getCode());
        Instant estonianTimeNow = TimeUtil.getEstonianTimeNow();
        for (Beach beach : beaches) {
            WeatherReport weatherReport = getApiWeatherReport(beach.getLat(), beach.getLng());
            createAndSaveWeatherInfo(beach, weatherReport, SUBSCRIPTION_TYPE_PAID, estonianTimeNow);
        }
    }


    private WeatherInfo createAndSaveWeatherInfo(Beach beach, WeatherReport weatherReport, String subscriptionType, Instant now) {
        WeatherInfo weatherInfo = createWeatherInfo(beach, weatherReport, subscriptionType, now);
        weatherInfoRepository.save(weatherInfo);
        return weatherInfo;
    }

    private WeatherInfo createWeatherInfo(Beach beach, WeatherReport weatherReport, String subscriptionType, Instant now) {
        WeatherInfo weatherInfo = weatherInfoMapper.toWeatherInfo(weatherReport);
        weatherInfo.setBeach(beach);
        weatherInfo.setTimestamp(now);
        weatherInfo.setSurfStatus(surfStatusCalculator.calculateSurfStatus(weatherInfo, beach));
        weatherInfo.setType(subscriptionType);
        return weatherInfo;
    }


    private void updateBeachSurfStatus(Beach beach, WeatherInfo weatherInfo) {
        beach.setSurfStatus(surfStatusCalculator.calculateSurfStatus(weatherInfo, beach));
        beach.setLastUpdate(Instant.now());
        beachRepository.save(beach);
    }

    private WeatherReport getApiWeatherReport(BigDecimal lat, BigDecimal lng) {
        String uri = "/v1/forecast?latitude=" + lat + "&longitude=" + lng + STANDARD_PARAMETERS;
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(WeatherReport.class)
                .block();
    }

}
