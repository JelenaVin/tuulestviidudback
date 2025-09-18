package ee.valiit.tuulestviidudback.persistance.comment;

import ee.valiit.tuulestviidudback.persistance.user.User;
import ee.valiit.tuulestviidudback.persistance.beach.Beach;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "comment", schema = "tuulest")
public class Comment {
    @Id
    @ColumnDefault("nextval('tuulest.comment_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "beach_id", nullable = false)
    private Beach beach;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status;

}
