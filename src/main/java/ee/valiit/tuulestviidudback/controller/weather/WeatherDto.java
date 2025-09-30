package ee.valiit.tuulestviidudback.controller.weather;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link ee.valiit.tuulestviidudback.persistance.weatherinfo.WeatherInfo}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDto implements Serializable {
    private Integer id;
    private Integer beachId;
    @NotNull
    private BigDecimal windSpeed;
    @NotNull
    private Integer windDirection;
    @NotNull
    private BigDecimal windGusts;
    @NotNull
    private BigDecimal temperature;
    @NotNull
    private BigDecimal precipitation;
    @NotNull
    private Instant timestamp;
    @NotNull
    @Size(max = 1)
    private String surfStatus;
    @NotNull
    @Size(max = 1)
    private String type;
}
