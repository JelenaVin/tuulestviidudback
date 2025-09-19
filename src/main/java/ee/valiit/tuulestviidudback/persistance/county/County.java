package ee.valiit.tuulestviidudback.persistance.county;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "county", schema = "tuulest")
public class County {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "sequence", nullable = false)
    private Integer sequence;

    @NotNull
    @Column(name = "lat", nullable = false, precision = 10, scale = 7)
    private BigDecimal lat;

    @NotNull
    @Column(name = "lng", nullable = false, precision = 10, scale = 7)
    private BigDecimal lng;

    @NotNull
    @Column(name = "zoom_level", nullable = false)
    private Integer zoomLevel;

}
