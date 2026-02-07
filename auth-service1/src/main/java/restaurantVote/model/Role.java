package restaurantVote.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class Role implements GrantedAuthority {
    @Id
    @SequenceGenerator(name = "idSeq", sequenceName = "id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idSeq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private List<User> users;

    @Override
    public String toString() {
        return "Role{" +
                "id: " + id + ", " +
                "name: " + name + "}";
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
