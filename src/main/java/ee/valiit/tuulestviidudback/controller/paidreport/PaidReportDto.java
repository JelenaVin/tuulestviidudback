package ee.valiit.tuulestviidudback.controller.paidreport;

import ee.valiit.tuulestviidudback.persistance.paidreport.PaidReport;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link PaidReport}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaidReportDto implements Serializable {
    private Integer id;
    private Integer userId;
    private Integer beachId;
    @NotNull
    @Size(max = 1)
    private String surfStatus;
    @NotNull
    private Instant lastUpdate;
}
