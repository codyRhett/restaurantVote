package restaurantVote.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import restaurantVote.model.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
