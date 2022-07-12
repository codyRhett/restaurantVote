package restaurantVote.Service;

import restaurantVote.Repository.VoteRepository;

public class VoteService {
    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }
}
