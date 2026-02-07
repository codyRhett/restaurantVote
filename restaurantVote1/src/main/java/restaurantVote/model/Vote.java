package restaurantVote.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @SequenceGenerator(name = "idSeq", sequenceName = "id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idSeq")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "rating")
    private Long rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "comment")
    private String comment;
}
