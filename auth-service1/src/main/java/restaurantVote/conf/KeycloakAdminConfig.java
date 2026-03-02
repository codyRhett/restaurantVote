package restaurantVote.conf;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.OAuth2Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Настройте бин Keycloak с правами администратора
@Configuration
public class KeycloakAdminConfig {

    @Bean
    public Keycloak keycloakAdmin() {
        return KeycloakBuilder.builder()
                .serverUrl("http://localhost:8080")  // URL вашего Keycloak
                .realm("master")                      // Realm для аутентификации (обычно master)
                .clientId("admin-cli")                 // Стандартный клиент для админ API
                .grantType(OAuth2Constants.PASSWORD)
                .username("admin")                     // Админ пользователь Keycloak
                .password("admin")                      // Пароль админа
                .build();
    }
}
