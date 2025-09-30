package ee.valiit.tuulestviidudback.persistance.subscription;

import ee.valiit.tuulestviidudback.persistance.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
    @Query("""
            select (count(s) > 0) from Subscription s
            where s.user = :user and s.dateFrom <= :todaysDate and :todaysDate <= s.dateTo""")
    boolean subscriptionExistsBy(User user, LocalDate todaysDate);


}