package ee.valiit.tuulestviidudback.persistance.county;

import ee.valiit.tuulestviidudback.controller.county.CountyDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CountyMapper {

    @Mapping(source = "id", target = "countyId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "sequence", target = "sequence")
    @Mapping(source = "lat", target = "lat")
    @Mapping(source = "lng", target = "lng")
    @Mapping(source = "zoomLevel", target = "zoomLevel")

    CountyDto toCountyDto(County county);

    List<CountyDto> toCountyDtos(List<County> counties);

}
