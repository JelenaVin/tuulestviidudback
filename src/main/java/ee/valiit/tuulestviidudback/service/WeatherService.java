package ee.valiit.tuulestviidudback.service;

import ee.valiit.tuulestviidudback.Status;
import ee.valiit.tuulestviidudback.controller.weather.apidto.WeatherReport;
import ee.valiit.tuulestviidudback.infrastructure.exception.PrimaryKeyNotFoundException;
import ee.valiit.tuulestviidudback.persistance.beach.Beach;
import ee.valiit.tuulestviidudback.persistance.beach.BeachRepository;
import ee.valiit.tuulestviidudback.persistance.paidreport.PaidReport;
import ee.valiit.tuulestviidudback.persistance.paidreport.PaidReportMapper;
import ee.valiit.tuulestviidudback.persistance.paidreport.PaidReportRepository;
import ee.valiit.tuulestviidudback.persistance.user.User;
import ee.valiit.tuulestviidudback.persistance.user.UserRepository;
import ee.valiit.tuulestviidudback.persistance.weatherinfo.WeatherInfo;
import ee.valiit.tuulestviidudback.persistance.weatherinfo.WeatherInfoMapper;
import ee.valiit.tuulestviidudback.persistance.weatherinfo.WeatherInfoRepository;
import jakarta.validation.constraints.NotNull;
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
    public static final String FIELD_NAME_USER_ID = "userId";
    private final WebClient webClient = WebClient.builder().baseUrl("https://api.open-meteo.com").build();
    private final WeatherInfoMapper weatherInfoMapper;
    private final WeatherInfoRepository weatherInfoRepository;
    private final BeachRepository beachRepository;
    private final SurfStatusCalculator surfStatusCalculator;
    private final PaidReportMapper paidReportMapper;
    private final PaidReportRepository paidReportRepository;
    private final UserRepository userRepository;
    private ee.valiit.tuulestviidudback.persistance.user.@NotNull User User;


    public void updateFreeWeatherInfo() {
        List<Beach> beaches = beachRepository.findBeachesBy(Status.ACTIVE.getCode());
        for (Beach beach : beaches) {
            WeatherReport weatherReport = getApiWeatherReport(beach.getLat(), beach.getLng());
            WeatherInfo weatherInfo = createAndSaveWeatherInfo(beach, weatherReport, SUBSCRIPTION_TYPE_FREE);
            updateBeachSurfStatus(beach, weatherInfo);
        }
    }

    public void updatePaidWeatherInfo(Integer userId) {
        List<Beach> beaches = beachRepository.findBeachesBy(Status.ACTIVE.getCode());
        for (Beach beach : beaches) {
            WeatherReport weatherReport = getApiWeatherReport(beach.getLat(), beach.getLng());
            WeatherInfo weatherInfo = createAndSaveWeatherInfo(beach, weatherReport, SUBSCRIPTION_TYPE_PAID);
            PaidReport paidReport = createAndSavePaidReport(beach, weatherInfo, userId);
            updatePaidBeachSurfStatus(beach, weatherInfo, paidReport);
        }
    }


    private WeatherInfo createAndSaveWeatherInfo(Beach beach, WeatherReport weatherReport, String subscriptionType) {
        WeatherInfo weatherInfo = createWeatherInfo(beach, weatherReport, subscriptionType);
        weatherInfoRepository.save(weatherInfo);
        return weatherInfo;
    }

    private WeatherInfo createWeatherInfo(Beach beach, WeatherReport weatherReport, String subscriptionType) {
        WeatherInfo weatherInfo = weatherInfoMapper.toWeatherInfo(weatherReport);
        weatherInfo.setBeach(beach);
        weatherInfo.setTimestamp(Instant.now());
        weatherInfo.setSurfStatus(surfStatusCalculator.calculateSurfStatus(weatherInfo, beach));
        weatherInfo.setType(subscriptionType);
        return weatherInfo;
    }


    private PaidReport createAndSavePaidReport(Beach beach, WeatherInfo weatherInfo, Integer userId) {
        PaidReport paidReport = createPaidReport(beach, weatherInfo, userId);
        paidReportRepository.save(paidReport);
        return paidReport;
    }

    private PaidReport createPaidReport(Beach beach, WeatherInfo weatherInfo, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException(FIELD_NAME_USER_ID, userId));
        PaidReport paidReport = new PaidReport();
        paidReport.setBeach(beach);
        paidReport.setUser(user);
        paidReport.setSurfStatus(surfStatusCalculator.calculateSurfStatus(weatherInfo, beach));
        paidReport.setLastUpdate(Instant.now());
        return paidReport;
    }

    private void updatePaidBeachSurfStatus(Beach beach, WeatherInfo weatherInfo, PaidReport paidReport) {
        paidReport.setSurfStatus(surfStatusCalculator.calculateSurfStatus(weatherInfo, beach));
        paidReport.setLastUpdate(Instant.now());
        paidReportRepository.save(paidReport);
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
                .block(); // blocking for simplicity
    }
}
