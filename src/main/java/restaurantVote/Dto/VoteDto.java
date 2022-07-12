package restaurantVote.Dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class VoteDto {
    private Long id;

    @NotNull
    private RestaurantDto restaurant;

    private Date dateCreated;

    @NotNull
    private Long rating;

    @NotNull
    private UserDto user;
}
