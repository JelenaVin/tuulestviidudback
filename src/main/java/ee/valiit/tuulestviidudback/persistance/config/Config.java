package ee.valiit.tuulestviidudback.persistance.config;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "config", schema = "tuulest")
public class Config {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "wind_speed_min", nullable = false, precision = 4, scale = 1)
    private BigDecimal windSpeedMin;

    @NotNull
    @Column(name = "wind_speed_max", nullable = false, precision = 4, scale = 1)
    private BigDecimal windSpeedMax;

}
