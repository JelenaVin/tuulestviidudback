package ee.valiit.tuulestviidudback.persistance.weather;

import ee.valiit.tuulestviidudback.controller.weather.apidto.WeatherResponse;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface WeatherMapper {


//    @Mapping(source = "current.time", target = "timestamp")
    @Mapping(source = "current.precipitation", target = "precipitation")
    @Mapping(source = "current.windSpeed10m", target = "windSpeed")
    @Mapping(source = "current.windDirection10m", target = "windDirection")
    @Mapping(source = "current.windGusts10m", target = "windGusts")
    @Mapping(source = "current.temperature2m", target = "temperature")
    Weather toWeather(WeatherResponse weatherResponse);

    WeatherResponse toDto(Weather weather);
}
