package ee.valiit.tuulestviidudback.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class WeatherScheduler {

	private final WeatherService weatherService;

	@Scheduled(cron = "${weather.update.cron}")
	public void scheduledWeatherUpdate() {
		log.info("Starting scheduled free weather info update");
		weatherService.updateFreeWeatherInfo();
		log.info("Completed scheduled free weather info update");
	}
}