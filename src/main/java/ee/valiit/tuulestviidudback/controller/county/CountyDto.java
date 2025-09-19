package ee.valiit.tuulestviidudback.controller.county;

import ee.valiit.tuulestviidudback.persistance.county.County;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link County}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountyDto implements Serializable {

    private Integer countyId;
    private String name;
    private Integer sequence;
    private BigDecimal lat;
    private BigDecimal lng;
    private Integer zoomLevel;
}
