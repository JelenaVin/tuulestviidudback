package ee.valiit.tuulestviidudback.persistance.userbeachimage;

import ee.valiit.tuulestviidudback.persistance.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "user_beach_image", schema = "tuulest")
public class UserBeachImage {
    @Id
    @ColumnDefault("nextval('tuulest.user_beach_image_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @Column(name = "image_data", nullable = false)
    private byte[] imageData;

    @NotNull
    @Column(name = "\"timestamp\"", nullable = false)
    private Instant timestamp;

}
