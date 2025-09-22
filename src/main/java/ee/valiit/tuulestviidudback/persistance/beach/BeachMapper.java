package ee.valiit.tuulestviidudback.persistance.beach;

import ee.valiit.tuulestviidudback.controller.beach.BeachDto;
import org.mapstruct.*;

import java.time.Instant;


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
    @Mapping(constant = "A", target = "beachStatus")
    @Mapping(expression = "java(Instant.now())", target = "lastUpdate")
    Beach toBeach (BeachDto beachDto);


    @Mapping(source = "name", target = "beachName")
    @Mapping(source = "lat", target = "lat")
    @Mapping(source = "lng", target = "lng")
    @Mapping(source = "windDirectionMin", target = "windDirectionMin")
    @Mapping(source = "windDirectionMax", target = "windDirectionMax")
    @Mapping(source = "windSpeedMin", target = "windSpeedMax")
    @Mapping(source = "description", target = "description")
    BeachDto toBeachDto(Beach beach);

}
