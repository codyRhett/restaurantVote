package restaurantVote.mapper;

import org.springframework.stereotype.Component;
import restaurantVote.Dto.UserDto;
import restaurantVote.model.Status;
import restaurantVote.model.User;

@Component
public class UserMapper {

    public User fromDto(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastname(userDto.getLastName());
        user.setEmail(userDto.getEmail());

        if (userDto.getStatus() != null) {
            user.setStatus(userDto.getStatus());
        } else {
            user.setStatus(String.valueOf(Status.ACTIVE));
        }

        user.setPasswordConfirm(userDto.getPassword());

        return user;
    }

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastname());
        userDto.setEmail(user.getEmail());

        if (user.getStatus() != null) {
            userDto.setStatus(String.valueOf(user.getStatus()));
        } else {
            userDto.setStatus(String.valueOf(Status.ACTIVE));
        }

        userDto.setPassword(user.getPassword());

        return userDto;
    }
}
