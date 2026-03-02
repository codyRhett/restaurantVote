package restaurantVote.rest;

import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

import restaurantVote.dto.PageResponse;
import restaurantVote.dto.UserDto;
import restaurantVote.service.KeycloakUserService;

@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {

    @Autowired
    private KeycloakUserService userService;

    @GetMapping
    public PageResponse<UserDto> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int pageSize,
            @RequestParam(required = false) String search) {

        List<UserRepresentation> users;

        if (search != null && !search.isEmpty()) {
            users = userService.searchUsers(search, page, pageSize);
        } else {
            users = userService.getAllUsers(page, pageSize);
        }

        List<UserDto> userDtos = users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        int total = userService.countUsers();

        return new PageResponse<>(userDtos, page, pageSize, total);
    }

    @GetMapping("/search")
    public List<UserDto> searchUsers(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int pageSize) {

        List<UserRepresentation> users = userService.searchUsers(
                username, firstName, lastName, email, page, pageSize);

        return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private UserDto convertToDto(UserRepresentation user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEnabled(user.isEnabled());
        dto.setCreatedTimestamp(user.getCreatedTimestamp());
        dto.setEmailVerified(user.isEmailVerified());
        return dto;
    }
}
