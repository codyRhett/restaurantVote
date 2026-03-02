package restaurantVote.service;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restaurantVote.dto.PageResponse;

import java.util.List;

@Service
public class KeycloakUserService {

    @Autowired
    private Keycloak keycloakAdmin;

    private static final String REALM_NAME = "your-realm";

    /**
     * Поиск пользователей по строке (ищет по username, email, атрибутам)
     */
    public List<UserRepresentation> searchUsers(String searchText, int page, int pageSize) {
        int first = page * pageSize;

        return keycloakAdmin.realm(REALM_NAME)
                .users()
                .search(searchText, first, pageSize);
    }

    /**
     * Поиск пользователей с пагинацией по конкретным полям
     */
    public List<UserRepresentation> searchUsers(String username,
                                                String firstName,
                                                String lastName,
                                                String email,
                                                int page,
                                                int pageSize) {
        int first = page * pageSize;

        return keycloakAdmin.realm(REALM_NAME)
                .users()
                .search(username, firstName, lastName, email, first, pageSize);
    }

    /**
     * Получить ВСЕХ пользователей (с пагинацией)
     */
    public List<UserRepresentation> getAllUsers(int page, int pageSize) {
        int first = page * pageSize;

        return keycloakAdmin.realm(REALM_NAME)
                .users()
                .list(first, pageSize);
    }

    /**
     * Подсчитать общее количество пользователей
     */
    public int countUsers() {
        return keycloakAdmin.realm(REALM_NAME)
                .users()
                .count();
    }

    /**
     * Пример использования для UI с пагинацией
     */
    public PageResponse<UserRepresentation> getUsersPage(int page, int pageSize) {
        int first = page * pageSize;

        List<UserRepresentation> users = keycloakAdmin.realm(REALM_NAME)
                .users()
                .list(first, pageSize);

        int total = countUsers();

        return new PageResponse<>(users, page, pageSize, total);
    }
}