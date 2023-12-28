package restaurantVote.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import restaurantVote.dto.UserDto;
import restaurantVote.mapper.UserMapper;
import restaurantVote.model.RestaurantVoteList;
import restaurantVote.model.Role;
import restaurantVote.model.User;
import restaurantVote.repository.RoleRepository;
import restaurantVote.service.RestaurantVoteService;
import restaurantVote.service.UserService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final RestaurantVoteService restaurantVoteService;

    @Autowired
    public UserRestController(UserService userService, UserMapper userMapper, RoleRepository roleRepository, RestaurantVoteService restaurantVoteService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.restaurantVoteService = restaurantVoteService;
    }

    @GetMapping(value = "/{id}/restaurants")
    public ModelAndView getRestaurantsVotedByUser(@PathVariable(name = "id") Long id) {
        List<RestaurantVoteList> restaurantVoteLists = restaurantVoteService.findByUserId(id);
        ModelAndView mav = new ModelAndView("restaurantUser");
        mav.addObject("restaurantVoteForm", restaurantVoteLists);
        return mav;
    }

    @GetMapping(value = "/{id}")
    public ModelAndView getUserById(@PathVariable(name = "id") Long id) {
        Optional<User> user = userService.findById(id);
//        if (user.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
        UserDto result = userMapper.toDto(user.orElseThrow());
        ModelAndView mav = new ModelAndView("user");
        List<Role> roles = roleRepository.findAll();
        mav.addObject("roles", roles);
        mav.addObject("userForm", result);
        return mav;
    }

    @PostMapping(path = "")
    public ModelAndView updateUser(@ModelAttribute("userForm") @Valid UserDto userDto, @Nullable Long[] roleIds) {
        List<Role> roles;
        if (roleIds != null) {
            roles = roleRepository.findAllById(Arrays.asList(roleIds));
        } else {
            roles = userService.findById(userDto.getId()).get().getRoles();
        }
        userDto.setRoles(roles);
        User user = userMapper.fromDto(userDto);
        try {
            userService.updateUser(user);
        } catch (EntityNotFoundException e) {
            String message = e.getMessage();
            ModelAndView modelAndView = new ModelAndView("error", "userForm" ,message);
            return modelAndView;
        }

        return new ModelAndView("redirect:/", "userForm", userDto);
    }
}
