package restaurantVote.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import restaurantVote.dto.UserDto;
import restaurantVote.mapper.UserMapper;
import restaurantVote.model.User;
import restaurantVote.service.UserService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserRestController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping(path = "")
    public ModelAndView registerUser(@ModelAttribute("userForm") @Valid UserDto userDto) {
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
