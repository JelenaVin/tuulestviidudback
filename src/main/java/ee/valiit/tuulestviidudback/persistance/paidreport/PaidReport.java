package ee.valiit.tuulestviidudback.persistance.paidreport;

import ee.valiit.tuulestviidudback.persistance.user.User;
import ee.valiit.tuulestviidudback.persistance.beach.Beach;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "paid_report", schema = "tuulest")
public class PaidReport {
    @Id
    @ColumnDefault("nextval('tuulest.paid_report_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "beach_id", nullable = false)
    private Beach beach;

    @Size(max = 10)
    @NotNull
    @Column(name = "surf_status", nullable = false, length = 10)
    private String surfStatus;

    @NotNull
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

}
