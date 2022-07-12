package restaurantVote.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import restaurantVote.Dto.VoteDto;
import restaurantVote.model.Vote;

@Component
public class VoteMapper {

    private final UserMapper userMapper;
    private final RestaurantMapper restaurantMapper;

    @Autowired
    public VoteMapper(UserMapper userMapper, RestaurantMapper restaurantMapper) {
        this.userMapper = userMapper;
        this.restaurantMapper = restaurantMapper;
    }

    public Vote fromDto(VoteDto voteDto) {
        Vote vote = new Vote();
        vote.setDateCreated(voteDto.getDateCreated());
        vote.setRating(voteDto.getRating());
        vote.setUser(userMapper.fromDto(voteDto.getUser()));
        vote.setRestaurant(restaurantMapper.fromDto(voteDto.getRestaurant()));
        vote.setId(voteDto.getId());

        return vote;
    }

    public VoteDto toDto(Vote vote) {
        VoteDto voteDto = new VoteDto();
        voteDto.setDateCreated(vote.getDateCreated());
        voteDto.setRating(vote.getRating());
        voteDto.setUser(userMapper.toDto(vote.getUser()));
        voteDto.setRestaurant(restaurantMapper.toDto(vote.getRestaurant()));
        voteDto.setId(vote.getId());

        return voteDto;
    }
}
