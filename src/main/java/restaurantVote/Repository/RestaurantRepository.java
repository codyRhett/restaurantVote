package restaurantVote.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import restaurantVote.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
