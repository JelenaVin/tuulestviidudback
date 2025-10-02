package ee.valiit.tuulestviidudback.persistance.beach;

import ee.valiit.tuulestviidudback.controller.beach.dto.BeachDto;
import ee.valiit.tuulestviidudback.controller.beach.dto.BeachInfo;
import ee.valiit.tuulestviidudback.persistance.weatherinfo.WeatherInfo;
import org.mapstruct.*;

import java.time.Instant;
import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, imports = {Instant.class})
public interface BeachMapper {

    @Mapping(source = "beachName", target = "name")
    @Mapping(source = "lat", target = "lat")
    @Mapping(source = "lng", target = "lng")
    @Mapping(source = "windDirectionMin", target = "windDirectionMin")
    @Mapping(source = "windDirectionMax", target = "windDirectionMax")
    @Mapping(source = "windSpeedMin", target = "windSpeedMin")
    @Mapping(source = "windSpeedMax", target = "windSpeedMax")
    @Mapping(source = "description", target = "description")
    @Mapping(constant = "java(Status.ACTIVE.getCode())", target = "beachStatus")
    @Mapping(expression = "java(Instant.now())", target = "lastUpdate")
    Beach toBeach(BeachDto beachDto);

    @Mapping(source = "user.id", target = "adminUserId")
    @Mapping(source = "county.id", target = "countyId")
    @Mapping(source = "name", target = "beachName")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "lat", target = "lat")
    @Mapping(source = "lng", target = "lng")
    @Mapping(source = "windDirectionMin", target = "windDirectionMin")
    @Mapping(source = "windDirectionMax", target = "windDirectionMax")
    @Mapping(source = "windSpeedMin", target = "windSpeedMin")
    @Mapping(source = "windSpeedMax", target = "windSpeedMax")
    BeachDto toBeachDto(Beach beach);

    @Mapping(source = "beach.id", target = "beachId")
    @Mapping(source = "beach.name", target = "beachName")
    @Mapping(source = "beach.lat", target = "lat")
    @Mapping(source = "beach.lng", target = "lng")
    @Mapping(source = "mapWindDirection", target = "actualDirection")
    @Mapping(source = "surfStatus", target = "surfStatus")
    BeachInfo toBeachInfo(WeatherInfo weatherInfo);

    List<BeachInfo> toBeachInfos(List<WeatherInfo> weatherInfo);

    @InheritConfiguration(name = "toBeach")
    @Mapping(ignore = true, target = "beachStatus")
    Beach partialUpdate(@MappingTarget Beach beach, BeachDto beachDto);


}
