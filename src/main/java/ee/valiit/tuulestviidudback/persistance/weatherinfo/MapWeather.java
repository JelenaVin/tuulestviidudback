package ee.valiit.tuulestviidudback.persistance.weatherinfo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link WeatherInfo}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapWeather implements Serializable {
    private Integer id;
    private Integer beachId;
    @NotNull
    private BigDecimal windSpeed;
    @NotNull
    private Integer windDirection;
    @NotNull
    @Size(max = 1)
    private String type;
}
