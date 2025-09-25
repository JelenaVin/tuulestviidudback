package ee.valiit.tuulestviidudback.persistance.weatherinfo;

import ee.valiit.tuulestviidudback.persistance.beach.Beach;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "weather_info", schema = "tuulest")
public class WeatherInfo {
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

    @Size(max = 1)
    @NotNull
    @Column(name = "surf_status", nullable = false, length = 1)
    private String surfStatus;

    @Size(max = 1)
    @NotNull
    @Column(name = "type", nullable = false, length = 1)
    private String type;

}
