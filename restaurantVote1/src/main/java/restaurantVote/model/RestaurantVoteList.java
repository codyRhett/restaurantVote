package restaurantVote.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "restaurant_vote_list")
public class RestaurantVoteList {

    @Id
    private Long id;

    @Column(name = "restaurant_id")
    private Long restaurantId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "rating")
    private Long rating;
}
