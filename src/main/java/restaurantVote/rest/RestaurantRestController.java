package restaurantVote.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import restaurantVote.dto.RestaurantDto;
import restaurantVote.dto.VoteDto;
import restaurantVote.mapper.RestaurantMapper;
import restaurantVote.mapper.VoteMapper;
import restaurantVote.model.Restaurant;
import restaurantVote.model.User;
import restaurantVote.model.Vote;
import restaurantVote.service.RestaurantService;
import restaurantVote.service.UserService;
import restaurantVote.service.VoteService;

import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantRestController {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;
    private final UserService userService;
    private final VoteService voteService;
    private final VoteMapper voteMapper;

    @Autowired
    public RestaurantRestController(RestaurantService restaurantService, RestaurantMapper restaurantMapper, UserService userService, VoteService voteService, VoteMapper voteMapper) {
        this.restaurantService = restaurantService;
        this.restaurantMapper = restaurantMapper;
        this.userService = userService;
        this.voteService = voteService;
        this.voteMapper = voteMapper;
    }

//    @PostMapping(path = "/")
//    public ResponseEntity<RestaurantDto> registerRestaurant(@RequestBody @Valid RestaurantDto restaurantDto) {
//        Restaurant registeredRestaurant = restaurantService.register(restaurantMapper.fromDto(restaurantDto));
//        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantMapper.toDto(registeredRestaurant));
//    }

//    @GetMapping(path = "/list")
//    public ResponseEntity<List<RestaurantDto>> list(Pageable pageable) {
//        List<RestaurantDto> registeredRestaurants = restaurantService.findAllSortedBy(pageable).stream()
//                .map(restaurantMapper::toDto)
//                .collect(Collectors.toList());
//        return new ResponseEntity<>(registeredRestaurants, HttpStatus.OK);
//    }


    // Вывод на экран полной инфы о ресторане
    @GetMapping("/{id}")
    public ModelAndView findRestaurantById(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        Restaurant restaurant = restaurantService.findById(id);
        ModelAndView mav = new ModelAndView("restaurant");
        RestaurantDto restaurantDto = restaurantMapper.toDto(restaurant);
        mav.addObject("restaurantForm", restaurantDto);

        User user = userService.findByUserName(principal.getName());
        VoteDto voteDto = new VoteDto();

        Optional<Vote> vote = voteService.findByRestaurantIdandUserId(id, user.getId());
        if (vote.isPresent()) {
            voteDto = voteMapper.toDto(vote.orElseThrow());
        } else {
            voteDto.setRating(restaurant.getRating());
            voteDto.setUserId(user.getId());
            voteDto.setRestaurant(restaurantDto);

        }
        mav.addObject("voteForm", voteDto);
        return mav;
    }

    @GetMapping("/list")
    public ModelAndView list(Pageable pageable) {
        List<RestaurantDto> registeredRestaurants = restaurantService.findAllSortedBy(pageable).stream()
                .map(restaurantMapper::toDto)
                .collect(Collectors.toList());

        ModelAndView mav = new ModelAndView("restaurants");
        mav.addObject("restaurantsForm", registeredRestaurants);
        return mav;
    }

    @GetMapping(path = "")
    public ModelAndView  getRestaurantForm() {
        ModelAndView mav = new ModelAndView("restaurantRegistration");
        mav.addObject("restaurantRegistrationForm", new RestaurantDto());
        return mav;
    }

    @PostMapping(path = "")
    public ModelAndView registerRestaurant(@ModelAttribute("restaurantRegistrationForm") @Valid RestaurantDto restaurantDto) {
        Restaurant restaurant = restaurantMapper.fromDto(restaurantDto);

        try {
            RestaurantDto registeredRestaurnat = restaurantMapper.toDto(restaurantService.register(restaurant));
        } catch (EntityExistsException e) {
            //ErrorRestaurant error = new ErrorRestaurant();
            String message = e.getMessage();
            ModelAndView modelAndView = new ModelAndView("error", "restaurantRegistrationForm" ,message);
            return modelAndView;
        }

        return new ModelAndView("redirect:/", "restaurantRegistrationForm", restaurantDto);
    }
}
