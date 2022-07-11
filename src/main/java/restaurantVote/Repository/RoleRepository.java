package restaurantVote.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import restaurantVote.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
