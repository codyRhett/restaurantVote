package restaurantVote.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restaurantVote.Dto.UserDto;
import restaurantVote.Service.UserService;
import restaurantVote.mapper.UserMapper;
import restaurantVote.model.User;

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

    @PostMapping(path = "/user")
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserDto userDto) {
        User user = userMapper.fromDto(userDto);
        UserDto registeredUser = userMapper.toDto(userService.register(user));

        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }
}
