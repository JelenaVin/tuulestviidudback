package ee.valiit.tuulestviidudback.persistance.weatherinfo;

import ee.valiit.tuulestviidudback.controller.weather.WeatherDto;
import ee.valiit.tuulestviidudback.controller.weather.apidto.WeatherReport;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface WeatherInfoMapper {

    @Mapping(source = "current.precipitation", target = "precipitation")
    @Mapping(source = "current.windSpeed10m", target = "windSpeed")
    @Mapping(source = "current.windDirection10m", target = "windDirection")
    @Mapping(source = "current.windGusts10m", target = "windGusts")
    @Mapping(source = "current.temperature2m", target = "temperature")
    WeatherInfo toWeatherInfo(WeatherReport weatherReport);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "beach.id", target = "beachId")
    @Mapping(source = "windSpeed", target = "windSpeed")
    @Mapping(source = "windDirection", target = "windDirection")
    @Mapping(source = "windGusts", target = "windGusts")
    @Mapping(source = "temperature", target = "temperature")
    @Mapping(source = "precipitation", target = "precipitation")
    @Mapping(source = "timestamp", target = "timestamp")
    @Mapping(source = "surfStatus", target = "surfStatus")
    @Mapping(source = "type", target = "type")
    WeatherDto toWeatherDto(WeatherInfo weatherInfo);
    List<WeatherDto> toWeatherDtos(List<WeatherInfo> weathers);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "beach.id", target = "beachId")
    @Mapping(source = "windSpeed", target = "windSpeed")
    @Mapping(source = "windDirection", target = "windDirection")
    @Mapping(source = "type", target = "type")
    MapWeather toMapWeather (WeatherInfo weatherInfo);
    List<MapWeather> toMapWeathers(List<WeatherInfo> weathers);


}
