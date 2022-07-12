package restaurantVote.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurantVote.Dto.RestaurantDto;
import restaurantVote.Dto.VoteDto;
import restaurantVote.Repository.VoteRepository;
import restaurantVote.Service.RestaurantService;
import restaurantVote.mapper.RestaurantMapper;
import restaurantVote.mapper.VoteMapper;
import restaurantVote.model.Restaurant;
import restaurantVote.model.Vote;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantRestController {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;
    private final VoteRepository voteRepository;
    private final VoteMapper voteMapper;

    @Autowired
    public RestaurantRestController(RestaurantService restaurantService, RestaurantMapper restaurantMapper, VoteRepository voteRepository, VoteMapper voteMapper) {
        this.restaurantService = restaurantService;
        this.restaurantMapper = restaurantMapper;
        this.voteRepository = voteRepository;
        this.voteMapper = voteMapper;
    }

    @PostMapping(path = "/")
    public ResponseEntity<RestaurantDto> registerRestaurant(@RequestBody @Valid RestaurantDto restaurantDto) {
        Restaurant registeredRestaurant = restaurantService.register(restaurantMapper.fromDto(restaurantDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantMapper.toDto(registeredRestaurant));
    }

    @PostMapping(path = "{id}/rating/{rating}")
    public ResponseEntity<RestaurantDto> updateRating(@PathVariable(name = "id") Long id, @PathVariable(name = "rating") Long rating) {
        return ResponseEntity.status(HttpStatus.OK).body(restaurantMapper.toDto(restaurantService.updateRating(id, rating)));
    }

    @PostMapping(path = "/vote")
    public ResponseEntity<RestaurantDto> updateRating(@RequestBody @Valid VoteDto voteDto) {
        Vote vote = voteMapper.fromDto(voteDto);
        voteRepository.save(voteMapper.fromDto(voteDto));

        return null;
    }
}
