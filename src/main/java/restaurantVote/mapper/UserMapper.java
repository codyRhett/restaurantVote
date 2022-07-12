package restaurantVote.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import restaurantVote.Dto.UserDto;
import restaurantVote.Dto.VoteDto;
import restaurantVote.model.Status;
import restaurantVote.model.User;
import restaurantVote.model.Vote;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private VoteMapper voteMapper;

    public User fromDto(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastname(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setId(userDto.getId());

        if (userDto.getVotes() != null) {
            List<Vote> listVotes = userDto.getVotes().stream()
                    .map(voteMapper::fromDto).collect(Collectors.toList());
            user.setVotes(listVotes);
        }

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
        userDto.setId(user.getId());

        if (user.getVotes() != null) {
            List<VoteDto> listVotes = user.getVotes().stream()
                    .map(voteMapper::toDto).collect(Collectors.toList());
            userDto.setVotes(listVotes);
        }

        if (user.getStatus() != null) {
            userDto.setStatus(String.valueOf(user.getStatus()));
        } else {
            userDto.setStatus(String.valueOf(Status.ACTIVE));
        }

        userDto.setPassword(user.getPassword());

        return userDto;
    }
}
