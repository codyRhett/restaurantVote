package restaurantVote.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import restaurantVote.Dto.VoteDto;
import restaurantVote.Service.UserService;
import restaurantVote.model.User;
import restaurantVote.model.Vote;

@Component
public class VoteMapper {

    private final UserMapper userMapper;
    private final RestaurantMapper restaurantMapper;
    private final UserService userService;

    @Autowired
    public VoteMapper(UserMapper userMapper, RestaurantMapper restaurantMapper, UserService userService) {
        this.userMapper = userMapper;
        this.restaurantMapper = restaurantMapper;
        this.userService = userService;
    }

    public Vote fromDto(VoteDto voteDto) {
        Vote vote = new Vote();
        vote.setDateCreated(voteDto.getDateCreated());
        vote.setRating(voteDto.getRating());

        User user = userService.findById(voteDto.getUserId()).orElseThrow();
        vote.setUser(user);

        vote.setRestaurant(restaurantMapper.fromDto(voteDto.getRestaurant()));
        vote.setId(voteDto.getId());

        return vote;
    }

    public VoteDto toDto(Vote vote) {
        VoteDto voteDto = new VoteDto();
        voteDto.setDateCreated(vote.getDateCreated());
        voteDto.setRating(vote.getRating());

        voteDto.setUserId(vote.getUser().getId());
        voteDto.setRestaurant(restaurantMapper.toDto(vote.getRestaurant()));
        voteDto.setId(vote.getId());

        return voteDto;
    }
}
