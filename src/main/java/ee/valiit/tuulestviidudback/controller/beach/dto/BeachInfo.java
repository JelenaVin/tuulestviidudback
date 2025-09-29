package ee.valiit.tuulestviidudback.controller.beach.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeachInfo implements Serializable {
    private Integer beachId;
    private String beachName;
    private BigDecimal lat;
    private BigDecimal lng;
    private Integer actualDirection;
    private String surfStatus;
}
