package restaurantVote.dto;

import lombok.Data;
import restaurantVote.model.Role;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UserDto {
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String password;

    @NotNull
    private String email;

    private String status;

    private List<Role> roles;
}
