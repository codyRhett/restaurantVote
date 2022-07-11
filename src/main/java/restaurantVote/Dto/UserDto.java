package restaurantVote.Dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

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
}
