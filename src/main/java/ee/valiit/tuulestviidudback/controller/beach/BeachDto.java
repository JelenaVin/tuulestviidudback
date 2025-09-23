package ee.valiit.tuulestviidudback.controller.beach;

import ee.valiit.tuulestviidudback.persistance.beach.Beach;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link Beach}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeachDto implements Serializable {
    @NotNull
    @Min(1)
    private Integer adminUserId;

    @NotNull
    @Min(1)
    private Integer countyId;

    @NotNull
    @Size(max = 255)
    private String beachName;

    @NotNull
    @Size(max = 1000)
    private String description;

    @NotNull
    private BigDecimal lat;
    @NotNull
    private BigDecimal lng;
    @NotNull
    private Integer windDirectionMin;
    @NotNull
    private Integer windDirectionMax;
    @NotNull
    private BigDecimal windSpeedMin;
    @NotNull
    private BigDecimal windSpeedMax;

    private String imageData;

}
