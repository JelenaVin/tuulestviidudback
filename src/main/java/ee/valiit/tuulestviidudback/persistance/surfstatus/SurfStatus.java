package ee.valiit.tuulestviidudback.persistance.surfstatus;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "surf_status", schema = "tuulest")
public class SurfStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "surf_status_id_gen")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 1)
    @NotNull
    @Column(name = "code", nullable = false, length = 1)
    private String code;

    @NotNull
    @Column(name = "wind_speed_min", nullable = false)
    private Integer windSpeedMin;

    @NotNull
    @Column(name = "wind_speed_max", nullable = false)
    private Integer windSpeedMax;

}
