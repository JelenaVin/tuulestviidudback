package ee.valiit.tuulestviidudback.persistance.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.username = :username and u.password = :password")
    Optional<User> findUserBy(String username, String password);

    @Query("select u from User u where u.id = :userId and u.role.name = 'admin'")
    Optional<User> findAdminUserBy(Integer userId);
}








