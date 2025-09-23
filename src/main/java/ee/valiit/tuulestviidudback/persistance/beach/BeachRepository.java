package ee.valiit.tuulestviidudback.persistance.beach;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BeachRepository extends JpaRepository<Beach, Integer> {

    @Query("select b from Beach b where b.beachStatus = :beachStatus order by b.county.sequence, b.name")
    List<Beach> findBeachesBy(String beachStatus);

}
