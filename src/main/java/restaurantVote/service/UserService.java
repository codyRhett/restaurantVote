package restaurantVote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import restaurantVote.repository.RoleRepository;
import restaurantVote.repository.UserRepository;
import restaurantVote.model.Role;
import restaurantVote.model.Status;
import restaurantVote.model.User;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName).orElseThrow();
    }

    public User register(User user) {
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

    public void deleteById(Long id) {
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
