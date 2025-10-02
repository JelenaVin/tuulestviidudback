package ee.valiit.tuulestviidudback.persistance.weatherinfo;

import ee.valiit.tuulestviidudback.controller.beachweather.dto.BeachWeather;
import ee.valiit.tuulestviidudback.controller.beachweather.dto.BeachWeatherInfo;
import ee.valiit.tuulestviidudback.controller.weather.apidto.WeatherReport;
import org.mapstruct.*;

import java.math.BigDecimal;
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


    @Mapping(source = "id", target = "weatherInfoId")
    @Mapping(source = "beach.id", target = "beachId")
    @Mapping(source = "beach.name", target = "beachName")
    @Mapping(source = "beach.lat", target = "lat")
    @Mapping(source = "beach.lng", target = "lng")
    @Mapping(source = "mapWindDirection", target = "actualDirection")
    @Mapping(source = "surfStatus", target = "surfStatus")
    BeachWeather toWeatherDto(WeatherInfo weatherInfo);



    List<BeachWeather> toWeatherDtos(List<WeatherInfo> weathers);


    @Mapping(source = "id", target = "beachId")
    @Mapping(source = "beach.name", target = "beachName")
    @Mapping(source = "beach.county.name", target = "countyName")
    @Mapping(source = "windGusts", target = "windGusts")
    @Mapping(source = "windSpeed", target = "windSpeed")
    @Mapping(source = "windDirectionFrom", target = "windDirectionDescription", qualifiedByName = "convertToWindDirectionDescription")
    @Mapping(source = "temperature", target = "airTemperature")
    @Mapping(source = "precipitation", target = "precipitation")
    @Mapping(source = "surfStatus", target = "surfStatus")
    @Mapping(source = "beach.description", target = "beachDescription")
    @Mapping(constant = "", target = "beachImage")
    BeachWeatherInfo toBeachWeatherInfo(WeatherInfo weatherInfo);



    @Named("calculateWindDirectionTo")
    static Integer calculateMapWindDirection(Integer windDirectionFrom) {
        return (windDirectionFrom + 90) % 360;
    }

    @Named("convertToWindDirectionDescription")
    static String convertToWindDirectionDescription(Integer windDirectionFrom) {

        if (windDirectionFrom == null) {
            return "";
        }
        int windDirection = windDirectionFrom % 360;

        if (338 <= windDirection && windDirection <= 22) {
            return "N";
        } else if (23 <= windDirection && windDirection <= 67) {
            return "NE";
        } else if (68 <= windDirection && windDirection <= 112) {
            return "E";
        } else if (113 <= windDirection && windDirection <= 157) {
            return "SE";
        } else if (158 <= windDirection && windDirection <= 202) {
            return "S";
        } else if (203 <= windDirection && windDirection <= 247) {
            return "SW";
        } else if (248 <= windDirection && windDirection <= 292) {
            return "W";
        } else if ( 293<= windDirection && windDirection <= 337) {
            return "NW";
        }

        return "";
    }

}
