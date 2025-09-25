package ee.valiit.tuulestviidudback.persistance.beachimage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BeachImageRepository extends JpaRepository<BeachImage, Integer> {


    @Query("select b from BeachImage b where b.beach.id = :beachId")
    Optional<BeachImage> findBeachImageBy(Integer beachId);
}
