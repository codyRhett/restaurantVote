package restaurantVote.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import restaurantVote.model.Restaurant;
import restaurantVote.repository.RestaurantRepository;

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

    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id).orElseThrow();
    }

    public Page<Restaurant> findAllSortedBy(Pageable pageable) {
        return restaurantRepository.findAll(pageable);
    }
}
