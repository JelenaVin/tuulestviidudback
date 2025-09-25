package ee.valiit.tuulestviidudback.service;

import ee.valiit.tuulestviidudback.persistance.beach.Beach;
import ee.valiit.tuulestviidudback.persistance.surfstatus.SurfStatus;
import ee.valiit.tuulestviidudback.persistance.surfstatus.SurfStatusRepository;
import ee.valiit.tuulestviidudback.persistance.weatherinfo.WeatherInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SurfStatusCalculator {

    public static final String STATUS_NO_GO = "N";
    private final SurfStatusRepository surfStatusRepository;


    public String calculateSurfStatus(WeatherInfo weatherInfo, Beach beach) {
        if (windDirectionIsWithinRequirements(weatherInfo.getWindDirection(), beach.getWindDirectionMin(), beach.getWindDirectionMax())) {
            return getStatusByWindSpeed(weatherInfo.getWindSpeed(), beach.getWindSpeedMin(), beach.getWindSpeedMax());
        }
        return STATUS_NO_GO;
    }

    private static boolean windDirectionIsWithinRequirements(Integer actualDirection, Integer minDirection, Integer maxDirection) {
        return minDirection <= actualDirection && actualDirection <= maxDirection;
    }

    private String getStatusByWindSpeed(BigDecimal actualWindSpeed, Integer requiredWindSpeedMin, Integer requiredWindSpeedMax) {
        List<SurfStatus> surfStatuses = surfStatusRepository.findAll();

        for (SurfStatus surfStatus : surfStatuses) {
            if (windSpeedIsWithinRequirements(actualWindSpeed, requiredWindSpeedMin, requiredWindSpeedMax)) {
                return surfStatus.getCode();
            }
        }

        return STATUS_NO_GO;
    }

    private boolean windSpeedIsWithinRequirements(BigDecimal actualSpeed, Integer minSpeed, Integer maxSpeed) {
        return new BigDecimal(minSpeed).compareTo(actualSpeed) <= 0 && actualSpeed.compareTo(new BigDecimal(maxSpeed)) <= 0;
    }

}
