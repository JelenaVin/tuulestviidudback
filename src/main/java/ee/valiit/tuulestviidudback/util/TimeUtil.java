package ee.valiit.tuulestviidudback.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class TimeUtil {

    public static Instant getEstonianTimeNow() {
        return Instant.now().plus(3, ChronoUnit.HOURS);
    }
}
