package ee.valiit.tuulestviidudback.persistance.paidreport;

import ee.valiit.tuulestviidudback.controller.paidreport.PaidReportDto;
import ee.valiit.tuulestviidudback.persistance.beach.Beach;
import ee.valiit.tuulestviidudback.persistance.user.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaidReportMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "beach.id", target = "beachId")
    @Mapping(source = "surfStatus", target = "surfStatus")
    @Mapping(source = "lastUpdate", target = "lastUpdate")
    PaidReportDto toPaidReportDto(PaidReport paidReport);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "beachId", target = "beach.id")
    @Mapping(source = "surfStatus", target = "surfStatus")
    @Mapping(source = "lastUpdate", target = "lastUpdate")
    PaidReport toPaidReport(PaidReportDto paidReportDto);

}
