package ee.valiit.tuulestviidudback.persistance.county;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CountyRepository extends JpaRepository<County, Integer> {


    @Query("select c from County c where c.id = :id")
    County findCountyById( Integer id);


}
