package ee.valiit.tuulestviidudback.controller.beachweather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeachWeatherInfo {
    private Integer beachId;
    private String beachName;
    private String countyName;
    private BigDecimal windGusts;
    private BigDecimal windSpeed;
    private String windDirectionDescription;
    private BigDecimal airTemperature;
    private BigDecimal precipitation;
    private String surfStatus;
    private String beachDescription;
    private String beachImage;

}
