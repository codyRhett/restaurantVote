package restaurantVote.Service;

import org.springframework.stereotype.Service;
import restaurantVote.Repository.VoteRepository;
import restaurantVote.model.Vote;

import java.util.List;

@Service
public class VoteService {
    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Vote save(Vote vote) {
        return voteRepository.save(vote);
    }

    public List<Vote> findByUserId(Long userId) {
        return voteRepository.findByUserId(userId);
    }
}
