package restaurantVote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import restaurantVote.model.Role;
import restaurantVote.model.Status;
import restaurantVote.model.User;
import restaurantVote.repository.RoleRepository;
import restaurantVote.repository.UserRepository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EntityManager em;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, EntityManager em) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.em = em;
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName).orElseThrow();
    }

    public User register(User user) throws EntityExistsException {
        if (userRepository.findByUserName(user.getUsername()).isPresent()) {
            throw new EntityExistsException("Entity with userName: " + user.getUsername() + " already exists");
        }

        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(roleUser);

        user.setPasswordConfirm(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        user.setStatus(String.valueOf(Status.ACTIVE));

        return userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public void deleteById(Long id) {
        Query query = em.createNativeQuery("DELETE FROM user_roles WHERE user_roles.user_id = ?");
        query.setParameter(1, id);
        query.executeUpdate();

        query = em.createNativeQuery("DELETE FROM votes WHERE votes.user_id = ?");
        query.setParameter(1, id);
        query.executeUpdate();

        userRepository.deleteById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username).orElseThrow(() -> new EntityNotFoundException("Entity with userName " + username + " not found"));
    }
}
