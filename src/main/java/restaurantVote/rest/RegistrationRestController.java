package restaurantVote.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import restaurantVote.dto.UserDto;
import restaurantVote.mapper.UserMapper;
import restaurantVote.model.User;
import restaurantVote.service.UserService;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/registration")
public class RegistrationRestController {

    private final UserMapper userMapper;
    private final UserService userService;

    @Autowired
    public RegistrationRestController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping("")
    public ModelAndView getHome() {
        ModelAndView mav = new ModelAndView("registration");
        mav.addObject("userForm", new UserDto());
        return mav;
    }


    @PostMapping(path = "/user")
    public ModelAndView registerUser(@ModelAttribute("userForm") @Valid UserDto userDto) {
        User user = userMapper.fromDto(userDto);

        try {
            UserDto registeredUser = userMapper.toDto(userService.register(user));
        } catch (EntityExistsException e) {
            //ErrorRestaurant error = new ErrorRestaurant();
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
