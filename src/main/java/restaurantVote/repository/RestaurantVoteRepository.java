package restaurantVote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import restaurantVote.model.RestaurantVoteList;

import java.util.List;

public interface RestaurantVoteRepository extends JpaRepository<RestaurantVoteList, Long> {
    List<RestaurantVoteList> findAllByRestaurantId(Long restaurantId);
}
