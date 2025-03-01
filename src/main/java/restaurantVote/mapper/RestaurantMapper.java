package restaurantVote.mapper;

import org.springframework.stereotype.Component;
import restaurantVote.dto.RestaurantDto;
import restaurantVote.model.Restaurant;

@Component
public class RestaurantMapper {

    public Restaurant fromDto(RestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDto.getName());
        restaurant.setCuisine(restaurantDto.getCuisine());
        restaurant.setRating(restaurantDto.getRating());
        restaurant.setId(restaurantDto.getId());

        return restaurant;
    }

    public RestaurantDto toDto(Restaurant restaurant) {
        RestaurantDto restaurantDto = new RestaurantDto();

        restaurantDto.setName(restaurant.getName());
        restaurantDto.setCuisine(restaurant.getCuisine());
        restaurantDto.setRating(restaurant.getRating());
        restaurantDto.setId(restaurant.getId());

        return restaurantDto;
    }
}
