package restaurantVote.Service;

import org.springframework.stereotype.Service;
import restaurantVote.Repository.RestaurantRepository;
import restaurantVote.model.Restaurant;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant register(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRating(Long id, Long rating) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        restaurant.setRating(rating);
        restaurantRepository.save(restaurant);
        return restaurant;
    }
}
