package restaurantVote.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RestaurantDto {
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String cuisine;

    private Long rating;
}
