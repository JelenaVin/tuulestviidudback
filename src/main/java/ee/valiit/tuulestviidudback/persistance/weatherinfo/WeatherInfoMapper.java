package ee.valiit.tuulestviidudback.persistance.weatherinfo;

import ee.valiit.tuulestviidudback.controller.weather.apidto.WeatherReport;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface WeatherInfoMapper {

    @Mapping(source = "current.precipitation", target = "precipitation")
    @Mapping(source = "current.windSpeed10m", target = "windSpeed")
    @Mapping(source = "current.windDirection10m", target = "windDirectionFrom")
    @Mapping(source = "current.windDirection10m", target = "mapWindDirection", qualifiedByName = "calculateWindDirectionTo")
    @Mapping(source = "current.windGusts10m", target = "windGusts")
    @Mapping(source = "current.temperature2m", target = "temperature")
    WeatherInfo toWeatherInfo(WeatherReport weatherReport);


    @Named("calculateWindDirectionTo")
    static Integer calculateMapWindDirection(Integer windDirectionFrom) {
        return (windDirectionFrom + 90) % 360;
    }

}
