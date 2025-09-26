package ee.valiit.tuulestviidudback.persistance.beach;

import ee.valiit.tuulestviidudback.Status;
import ee.valiit.tuulestviidudback.controller.beach.BeachDto;
import ee.valiit.tuulestviidudback.controller.county.CountyDto;
import ee.valiit.tuulestviidudback.persistance.county.County;
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
    Beach toBeach (BeachDto beachDto);

    @Mapping(source = "user.id", target = "adminUserId")
    @Mapping(source = "county.id", target = "countyId")
    @Mapping(source = "name", target = "beachName")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "lat", target = "lat")
    @Mapping(source = "lng", target = "lng")
    @Mapping(source = "windDirectionMin", target = "windDirectionMin")
    @Mapping(source = "windDirectionMax", target = "windDirectionMax")
    @Mapping(source = "windSpeedMin", target = "windSpeedMax")
    @Mapping(source = "beachStatus", target = "beachStatus")
    @Mapping(source = "surfStatus", target = "surfStatus")
    BeachDto toBeachDto(Beach beach);
    List<BeachDto> toBeachDtos(List<Beach> beaches);

    @InheritConfiguration(name = "toBeach")
    @Mapping(ignore = true, target = "beachStatus")
    Beach partialUpdate(@MappingTarget Beach beach, BeachDto beachDto);


}
