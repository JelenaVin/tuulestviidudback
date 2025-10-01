package ee.valiit.tuulestviidudback.controller.beachweather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link ee.valiit.tuulestviidudback.persistance.weatherinfo.WeatherInfo}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeachWeather implements Serializable {
    private Integer weatherInfoId;
    private Integer beachId;
    private String beachName;
    private BigDecimal lat;
    private BigDecimal lng;
    private Integer actualDirection;
    private String surfStatus;
}
