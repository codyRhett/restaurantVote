package restaurantVote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restaurantVote.model.RestaurantVoteList;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
public class RestaurantVoteService {
    private final EntityManager em;

    @Autowired
    public RestaurantVoteService(EntityManager em) {
        this.em = em;
    }

    public List findAll() {
        return em.createNativeQuery("SELECT * FROM restaurant_vote_list").getResultList();
    }

    public List<RestaurantVoteList> findByUserId(Long id) {
        Query query = em.createNativeQuery("SELECT * FROM restaurant_vote_list WHERE restaurant_vote_list.user_id = ?", RestaurantVoteList.class);
        query.setParameter(1, id);
        return query.getResultList();
    }

    public List<RestaurantVoteList> findByRestaurantId(Long id) {
        Query query = em.createNativeQuery("SELECT * FROM restaurant_vote_list WHERE restaurant_vote_list.restaurant_id = ?", RestaurantVoteList.class);
        query.setParameter(1, id);
        return query.getResultList();
    }

    public Double calculateRestaurantAvgRating(Long restaurant_id) {
        List<RestaurantVoteList> restaurantVoteList = findByRestaurantId(restaurant_id);

        var sum = restaurantVoteList.stream()
                .mapToInt(value -> Math.toIntExact(value.getRating()))
                .sum();

        return (double) (sum / (long) restaurantVoteList.size());
    }
}
