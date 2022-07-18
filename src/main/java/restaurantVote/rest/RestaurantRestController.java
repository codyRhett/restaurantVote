package restaurantVote.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurantVote.dto.RestaurantDto;
import restaurantVote.mapper.RestaurantMapper;
import restaurantVote.mapper.VoteMapper;
import restaurantVote.model.Restaurant;
import restaurantVote.repository.VoteRepository;
import restaurantVote.service.RestaurantService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping(path = "/list")
    public ResponseEntity<List<RestaurantDto>> list(Pageable pageable) {
        List<RestaurantDto> registeredRestaurants = restaurantService.findAllSortedBy(pageable).stream()
                .map(restaurantMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(registeredRestaurants, HttpStatus.OK);
    }

}
