package restaurantVote.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import restaurantVote.model.Restaurant;
import restaurantVote.repository.RestaurantRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class RestaurantService {

    private final EntityManager em;
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(EntityManager em, RestaurantRepository restaurantRepository) {
        this.em = em;
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant register(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public void deleteById(Long id) {
        Query query = em.createNativeQuery("DELETE FROM votes WHERE votes.restaurant_id = ?");
        query.setParameter(1, id);
        query.executeUpdate();

        restaurantRepository.deleteById(id);
    }

    public Restaurant updateRating(Long id, Long rating) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        restaurant.setRating(rating);
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    public <T> List<T> fromArrayToList(@NotNull List<Long> ids, List<String> fieldNames) {
        Query query = em.createNativeQuery("Select " + "id, " + String.join(",", fieldNames) + " from restaurants where restaurants.id in(:ids)");
        query.setParameter("ids", ids);
        return query.getResultList();
    }

    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id).orElseThrow();
    }

    public Page<Restaurant> findAllSortedBy(Pageable pageable) {
        return restaurantRepository.findAll(pageable);
    }
}
