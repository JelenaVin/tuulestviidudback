package ee.valiit.tuulestviidudback.service;

import ee.valiit.tuulestviidudback.controller.beachweather.dto.BeachWeather;
import ee.valiit.tuulestviidudback.controller.beachweather.dto.BeachWeatherReport;
import ee.valiit.tuulestviidudback.persistance.weatherinfo.WeatherInfo;
import ee.valiit.tuulestviidudback.persistance.weatherinfo.WeatherInfoMapper;
import ee.valiit.tuulestviidudback.persistance.weatherinfo.WeatherInfoRepository;
import ee.valiit.tuulestviidudback.util.TimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BeachWeatherService {

    private final WeatherInfoMapper weatherInfoMapper;
    private final WeatherInfoRepository weatherInfoRepository;

    private final WeatherService weatherService;


    public BeachWeatherReport getFreeBeachWeatherReport() {
        List<WeatherInfo> weatherInfos = getFreeWeatherInfos();
        return createBeachWeatherReport(weatherInfos);
    }

    public BeachWeatherReport getPaidBeachWeatherReport() {
        List<WeatherInfo> weatherInfos = getPaidWeatherInfos();
        return createBeachWeatherReport(weatherInfos);
    }


    private List<WeatherInfo> getFreeWeatherInfos() {
        Instant nowEstonian = TimeUtil.getEstonianTimeNow();
        Instant timeStampStart = nowEstonian.minus(2, ChronoUnit.HOURS);
        Instant timeStampEnd = nowEstonian.minus(1, ChronoUnit.HOURS);
        return weatherInfoRepository.findWeatherInfosBy("F", timeStampStart, timeStampEnd);
    }


    private List<WeatherInfo> getPaidWeatherInfos() {
        Instant nowEstonian = TimeUtil.getEstonianTimeNow();
        Instant timeStampStart = nowEstonian.minus(15, ChronoUnit.MINUTES);
        List<WeatherInfo> weathers = weatherInfoRepository.findWeatherInfosBy("P", timeStampStart, nowEstonian);
        if (weathers.isEmpty()) {
            weatherService.updatePaidWeatherInfo();
            weathers = weatherInfoRepository.findWeatherInfosBy("P", timeStampStart, nowEstonian);
        }
        return weathers;
    }


    private BeachWeatherReport createBeachWeatherReport(List<WeatherInfo> weatherInfos) {
        List<BeachWeather> beachWeathers = weatherInfoMapper.toWeatherDtos(weatherInfos);
        BeachWeatherReport beachWeatherReport = new BeachWeatherReport();
        beachWeatherReport.setWeatherTimeStamp(weatherInfos.get(0).getTimestamp().toString());
        beachWeatherReport.setBeachWeathers(beachWeathers);
        return beachWeatherReport;
    }






}
