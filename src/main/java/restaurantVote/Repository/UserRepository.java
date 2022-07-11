package restaurantVote.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import restaurantVote.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);
}
