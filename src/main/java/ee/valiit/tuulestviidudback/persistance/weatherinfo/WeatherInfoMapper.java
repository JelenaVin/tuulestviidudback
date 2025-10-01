package ee.valiit.tuulestviidudback.persistance.weatherinfo;

import ee.valiit.tuulestviidudback.controller.beachweather.dto.BeachWeather;
import ee.valiit.tuulestviidudback.controller.weather.apidto.WeatherReport;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface WeatherInfoMapper {

    @Mapping(source = "current.precipitation", target = "precipitation")
    @Mapping(source = "current.windSpeed10m", target = "windSpeed")
    @Mapping(source = "current.windDirection10m", target = "windDirectionFrom")
    @Mapping(source = "current.windDirection10m", target = "mapWindDirection", qualifiedByName = "calculateWindDirectionTo")
    @Mapping(source = "current.windGusts10m", target = "windGusts")
    @Mapping(source = "current.temperature2m", target = "temperature")
    WeatherInfo toWeatherInfo(WeatherReport weatherReport);

//
//    private Integer beachId;
//    private String beachName;
//    private BigDecimal lat;
//    private BigDecimal lng;
//    private Integer actualDirection;
//    private String surfStatus;
//
    @Mapping(source = "beach.id", target = "beachId")
    @Mapping(source = "beach.name", target = "beachName")
    @Mapping(source = "beach.lat", target = "lat")
    @Mapping(source = "beach.lng", target = "lng")
    @Mapping(source = "mapWindDirection", target = "actualDirection")
    @Mapping(source = "surfStatus", target = "surfStatus")
    BeachWeather toWeatherDto(WeatherInfo weatherInfo);

    List<BeachWeather> toWeatherDtos(List<WeatherInfo> weathers);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "beach.id", target = "beachId")
    @Mapping(source = "windSpeed", target = "windSpeed")
    @Mapping(source = "mapWindDirection", target = "windDirection")
    @Mapping(source = "type", target = "type")
    MapWeather toMapWeather(WeatherInfo weatherInfo);

    List<MapWeather> toMapWeathers(List<WeatherInfo> weathers);


    @Named("calculateWindDirectionTo")
    static Integer calculateMapWindDirection(Integer windDirectionFrom) {
        return (windDirectionFrom + 90) % 360;
    }

}
