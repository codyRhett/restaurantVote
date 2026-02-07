package restaurantVote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import restaurantVote.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);
}
