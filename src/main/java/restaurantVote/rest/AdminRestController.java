package restaurantVote.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurantVote.dto.UserDto;
import restaurantVote.service.UserService;
import restaurantVote.mapper.UserMapper;
import restaurantVote.model.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public AdminRestController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserDto result = userMapper.toDto(user.orElseThrow());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> registeredUsers = userService.findAllUsers().stream()
                .map(userMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(registeredUsers, HttpStatus.OK);
    }

    @DeleteMapping(value = "/user/delete/")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
