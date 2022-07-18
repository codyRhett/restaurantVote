package restaurantVote.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restaurantVote.dto.VoteDto;
import restaurantVote.mapper.VoteMapper;
import restaurantVote.model.Vote;
import restaurantVote.service.RestaurantService;
import restaurantVote.service.RestaurantVoteService;
import restaurantVote.service.VoteService;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/vote")
public class VoteController {

    private final VoteMapper voteMapper;
    private final VoteService voteService;
    private final RestaurantService restaurantService;
    private final RestaurantVoteService restaurantVoteService;

    public VoteController(VoteMapper voteMapper, VoteService voteService, RestaurantService restaurantService, RestaurantVoteService restaurantVoteService) {
        this.voteMapper = voteMapper;
        this.voteService = voteService;
        this.restaurantService = restaurantService;
        this.restaurantVoteService = restaurantVoteService;
    }

    @PostMapping(path = "/save")
    public ResponseEntity<VoteDto> vote(@RequestBody @Valid VoteDto voteDto) {
        // Если пользователь уже проголосовал за ресторан
        List<Vote> voteList = voteService.findByUserId(voteDto.getUserId());
        Vote vote = voteMapper.fromDto(voteDto);
        if (!voteList.isEmpty()) {
            // Если пользователь уже проголосовал
            // Смотрим - проголосовал ли он за выбранный ресторан
            if ((voteList.stream().noneMatch(vote1 -> Objects.equals(vote1.getRestaurant().getId(), voteDto.getRestaurant().getId())))) {
                // Если пользователь голосовал, но еще не голосовал за этот ресторан
                voteService.save(vote);
            } else {
                throw new ValidationException("You've already voted for restaurant with id = " + voteDto.getRestaurant().getId());
            }
        } else {
            // Если пользователь вообще не голосовал
            voteService.save(vote);
        }

        // Надо обновить общий рейтинг у ресторана
        Double avgRating = restaurantVoteService.calculateRestaurantAvgRating(voteDto.getRestaurant().getId());
        restaurantService.updateRating(voteDto.getRestaurant().getId(), avgRating.longValue());
        return ResponseEntity.status(HttpStatus.CREATED).body(voteDto);
    }
}
