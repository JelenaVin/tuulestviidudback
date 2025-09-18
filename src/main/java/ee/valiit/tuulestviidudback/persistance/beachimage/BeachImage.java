package ee.valiit.tuulestviidudback.persistance.beachimage;

import ee.valiit.tuulestviidudback.persistance.beach.Beach;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "beach_image", schema = "tuulest")
public class BeachImage {
    @Id
    @ColumnDefault("nextval('tuulest.beach_image_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "image_data")
    private byte[] imageData;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "beach_id", nullable = false)
    private Beach beach;

}
