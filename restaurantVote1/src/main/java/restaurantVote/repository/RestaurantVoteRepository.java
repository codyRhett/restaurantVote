package restaurantVote.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantVoteRepository extends JpaRepository<RestaurantVoteList, Long> {
    List<RestaurantVoteList> findAllByRestaurantId(Long restaurantId);
}
