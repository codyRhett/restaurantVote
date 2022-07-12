package restaurantVote.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "restaurants")
@Data
public class Restaurant {
    @Id
    @SequenceGenerator(name = "idSeq", sequenceName = "id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idSeq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cuisine")
    private String cuisine;

    @Column(name = "rating")
    private Long rating;
}
