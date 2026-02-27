package restaurantVote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import restaurantVote.model.Role;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByName(String name);
}
