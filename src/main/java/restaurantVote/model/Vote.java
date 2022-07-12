package restaurantVote.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @SequenceGenerator(name = "idSeq", sequenceName = "id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idSeq")
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "rating")
    private Long rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        String userId;
        if (user == null) {
            userId = "null";
        } else {
            userId = user.getId().toString();
        }
        return "Vote{" +
                "id=" + id +
                ", userId=" + userId +
                ", restaurant='" + restaurant + '\'' +
                ", dateCreated=" + dateCreated +
                ", rating=" + rating +
                '}';
    }
}
