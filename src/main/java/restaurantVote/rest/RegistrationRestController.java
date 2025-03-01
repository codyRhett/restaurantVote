package restaurantVote.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import restaurantVote.dto.UserDto;
import restaurantVote.mapper.RestaurantMapper;
import restaurantVote.mapper.UserMapper;
import restaurantVote.model.Role;
import restaurantVote.model.User;
import restaurantVote.repository.RoleRepository;
import restaurantVote.service.RestaurantService;
import restaurantVote.service.UserService;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/registration")
public class RegistrationRestController {

    private final UserMapper userMapper;
    private final UserService userService;
    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;
    private final RoleRepository roleRepository;

    @Autowired
    public RegistrationRestController(UserMapper userMapper, UserService userService, RestaurantService restaurantService, RestaurantMapper restaurantMapper, RoleRepository roleRepository) {
        this.userMapper = userMapper;
        this.userService = userService;
        this.restaurantService = restaurantService;
        this.restaurantMapper = restaurantMapper;
        this.roleRepository = roleRepository;
    }

    @GetMapping("")
    public ModelAndView registerUser() {
        ModelAndView mav = new ModelAndView("registration");
        mav.addObject("userForm", new UserDto());
        return mav;
    }


    @PostMapping(path = "/user")
    public ModelAndView registerUser(@ModelAttribute("userForm") @Valid UserDto userDto) {
        User user = userMapper.fromDto(userDto);
        Role role = roleRepository.findById(2L).orElseThrow();
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        user.setRoles(roleList);
        try {
            UserDto registeredUser = userMapper.toDto(userService.register(user));
        } catch (EntityExistsException e) {
            String message = e.getMessage();
            ModelAndView modelAndView = new ModelAndView("error", "userForm" ,message);
            return modelAndView;
        }

        return new ModelAndView("redirect:/", "userForm", userDto);
    }

//    @PostMapping(path = "/user")
//    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
//        User user = userMapper.fromDto(userDto);
//        UserDto registeredUser = userMapper.toDto(userService.register(user));
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
//    }
}
