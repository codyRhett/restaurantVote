package restaurantVote.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import restaurantVote.rest.UIController;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true) // Включает поддержку @Secured, @PreAuthorize и @RolesAllowed
public class SecurityConfig {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().authenticated()
//                )
//                .oauth2Login(Customizer.withDefaults());
//        return http.build();
//    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("Конфигурация применена");
        http
                .authorizeRequests(auth -> auth
                        .antMatchers("/oauth2/**", "/login/**").permitAll()
                        .antMatchers("/ui/api/**").authenticated()
                        .anyRequest().permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/oauth2/authorization/keycloak")
                        .successHandler((request, response, authentication) -> {
                            log.info("=== SUCCESS HANDLER ===");
                            log.info("User: " + authentication.getName());

                            // Проверяем, есть ли сохраненный запрос
                            SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
                            if (savedRequest != null) {
                                log.info("Сохраненный URL: " + savedRequest.getRedirectUrl());
                                log.info("Будет редирект на сохраненный URL");
                            } else {
                                log.info("Нет сохраненного URL, редирект на /my-special-dashboard");
                            }

                            response.sendRedirect("/my-special-dashboard");
                        })
                        .failureHandler((request, response, exception) -> {
                            log.info("Ошибка: " + exception.getMessage());
                            response.sendRedirect("/login?error");
                        })
                );

        return http.build();
    }


//    /**
//     * Цепочка 1: OAuth2 эндпоинты (самые важные - должны обрабатываться первыми)
//     */
//    @Bean
//    @Order(1)
//    public SecurityFilterChain oauth2FilterChain(HttpSecurity http) throws Exception {
//        http
//                .requestMatchers()
//                .antMatchers("/oauth2/**", "/login/**")
//                .and()
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().permitAll()  // Разрешаем все запросы к этим путям
//                )
//                .oauth2Login(Customizer.withDefaults());  // Включаем OAuth2 логин
//        return http.build();
//    }
//
//    // Конфигурация для UI части приложения (с сессией и редиректом на логин)
//    @Bean
//    @Order(2)
//    public SecurityFilterChain uiFilterChain(HttpSecurity http) throws Exception {
//        http
//                .antMatcher("/ui/**") // Применять эту цепочку только к путям, начинающимся с /ui
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().authenticated()
//                )
//                .oauth2Login(auth -> auth
//                        .loginPage("/oauth2/authorization/keycloak") // Явно указываем страницу логина
//                        .defaultSuccessUrl("/ui/dashboard", true)); // Куда после успешного входа); // Включаем стандартный OAuth2 логин (редирект на Keycloak)
//        return http.build();
//    }
//
//    // Конфигурация для API (stateless, проверка JWT)
//    @Bean
//    @Order(3)
//    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
//        http
//                .antMatcher("/api/**")
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().authenticated()
//                )
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // API не хранит сессии
//                .oauth2ResourceServer(oauth2 -> oauth2
//                        .jwt(Customizer.withDefaults()) // Включаем проверку JWT токенов
//                );
//        return http.build();
//    }

//    // Разрешаем все остальные запросы (например, на статику или публичные эндпоинты)
//    @Bean
//    @Order(3)
//    public SecurityFilterChain publicFilterChain(HttpSecurity http) throws Exception {
//        http
//                .antMatcher("/", "/public/**", "/error", "/webjars/**")
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().permitAll()
//                );
//        return http.build();
//    }
}