package restaurantVote.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restaurantVote.Dto.VoteDto;
import restaurantVote.Service.VoteService;
import restaurantVote.mapper.VoteMapper;
import restaurantVote.model.Vote;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/vote")
public class VoteController {

    private final VoteMapper voteMapper;
    private final VoteService voteService;

    public VoteController(VoteMapper voteMapper, VoteService voteService) {
        this.voteMapper = voteMapper;
        this.voteService = voteService;
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
                throw new ValidationException("You have already voted for restaurant with id = " + voteDto.getRestaurant().getId());
            }
        } else {
            // Если пользователь вообще не голосовал
            voteService.save(vote);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(voteDto);
    }
}
