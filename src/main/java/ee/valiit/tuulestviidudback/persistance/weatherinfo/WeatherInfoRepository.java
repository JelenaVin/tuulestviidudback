package ee.valiit.tuulestviidudback.persistance.weatherinfo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherInfoRepository extends JpaRepository<WeatherInfo, Integer> {
}
