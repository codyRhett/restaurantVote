package restaurantVote.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findByUserId(Long userId);
    List<Vote> findByRestaurantId(Long userId);
}
