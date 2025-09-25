package ee.valiit.tuulestviidudback.persistance.beach;

import ee.valiit.tuulestviidudback.persistance.county.County;
import ee.valiit.tuulestviidudback.persistance.user.User;
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
@Table(name = "beach", schema = "tuulest")
public class Beach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "county_id", nullable = false)
    private County county;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 1000)
    @Column(name = "description", length = 1000)
    private String description;

    @NotNull
    @Column(name = "lat", nullable = false, precision = 10, scale = 7)
    private BigDecimal lat;

    @NotNull
    @Column(name = "lng", nullable = false, precision = 10, scale = 7)
    private BigDecimal lng;

    @NotNull
    @Column(name = "wind_direction_min", nullable = false)
    private Integer windDirectionMin;

    @NotNull
    @Column(name = "wind_direction_max", nullable = false)
    private Integer windDirectionMax;

    @NotNull
    @Column(name = "wind_speed_min", nullable = false)
    private Integer windSpeedMin;

    @NotNull
    @Column(name = "wind_speed_max", nullable = false)
    private Integer windSpeedMax;

    @Size(max = 1)
    @NotNull
    @Column(name = "beach_status", nullable = false, length = 1)
    private String beachStatus;

    @Size(max = 1)
    @Column(name = "surf_status", length = 1)
    private String surfStatus;

    @Column(name = "last_update")
    private Instant lastUpdate;

}


