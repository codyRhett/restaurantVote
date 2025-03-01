package restaurantVote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restaurantVote.model.Vote;
import restaurantVote.repository.VoteRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final EntityManager em;

    @Autowired
    public VoteService(VoteRepository voteRepository, EntityManager em) {
        this.voteRepository = voteRepository;
        this.em = em;
    }

    public Vote save(Vote vote) {
        return voteRepository.save(vote);
    }

    public List<Vote> findByUserId(Long userId) {
        return voteRepository.findByUserId(userId);
    }

    public List<Vote> findByRestaurantId(Long restaurantId) {
        return voteRepository.findByRestaurantId(restaurantId);
    }

    public Optional<Vote> findByRestaurantIdandUserId(Long restaurantId, Long userId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Vote> cr = cb.createQuery(Vote.class);
        Root<Vote> root = cr.from(Vote.class);
        cr.select(root).where(cb.and(
                cb.equal(root.get("restaurant").get("id"), restaurantId),
                cb.equal(root.get("user").get("id"), userId))
        );

        TypedQuery<Vote> typedQuery = em.createQuery(cr);
        Optional<Vote> result = typedQuery.getResultList().stream().findFirst();

//        Query query = em.createNativeQuery("SELECT * FROM votes WHERE votes.restaurant_id = ? and votes.user_id = ?", Vote.class);
//        query.setParameter(1, restaurantId);
//        query.setParameter(2, userId);
//        Vote vote = (Vote) query.getSingleResult().;
        return result;
    }
}
