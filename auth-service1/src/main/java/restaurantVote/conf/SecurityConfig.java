package restaurantVote.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true) // Включает поддержку @Secured, @PreAuthorize и @RolesAllowed
public class SecurityConfig {


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().authenticated()
//                )
//                .oauth2Login(Customizer.withDefaults());
//        return http.build();
//    }


    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(auth -> auth
                        .antMatchers("/oauth2/**", "/login/**").permitAll()
                        .antMatchers("/ui/**").authenticated()
                        .anyRequest().permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/oauth2/authorization/keycloak")
                        .defaultSuccessUrl("/ui/dashboard", true)
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