package ee.valiit.tuulestviidudback.persistance.weatherinfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface WeatherInfoRepository extends JpaRepository<WeatherInfo, Integer> {

    @Query("select w from WeatherInfo w where w.type = :subscriptionType and w.timestamp between :timestampStart and :timestampEnd")
    List<WeatherInfo> findWeatherInfosBy(String subscriptionType, Instant timestampStart, Instant timestampEnd);


}
