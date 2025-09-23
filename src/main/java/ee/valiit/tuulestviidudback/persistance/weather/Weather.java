package ee.valiit.tuulestviidudback.persistance.weather;

import ee.valiit.tuulestviidudback.persistance.beach.Beach;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "weather", schema = "tuulest")
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "beach_id", nullable = false)
    private Beach beach;

    @NotNull
    @Column(name = "wind_speed", nullable = false, precision = 4, scale = 1)
    private BigDecimal windSpeed;

    @NotNull
    @Column(name = "wind_direction", nullable = false)
    private Integer windDirection;

    @NotNull
    @Column(name = "wind_gusts", nullable = false, precision = 4, scale = 1)
    private BigDecimal windGusts;

    @NotNull
    @Column(name = "temperature", nullable = false, precision = 4, scale = 1)
    private BigDecimal temperature;

    @NotNull
    @Column(name = "precipitation", nullable = false, precision = 4, scale = 1)
    private BigDecimal precipitation;

    @NotNull
    @Column(name = "\"timestamp\"", nullable = false)
    private Instant timestamp;

    @Size(max = 10)
    @NotNull
    @Column(name = "surf_status", nullable = false, length = 10)
    private String surfStatus;

    @Size(max = 4)
    @NotNull
    @Column(name = "type", nullable = false, length = 4)
    private String type;

}
