package restaurantVote.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import restaurantVote.service.UserService;

@Configuration
@EnableWebSecurity
public class AppConfiguration extends WebSecurityConfigurerAdapter {

    private static final String ADMIN_ENDPOINT = "/api/admin/**";
    private static final String REGISTER_ENDPOINT = "/api/registration/**";
    private static final String RESTAURANT_ENDPOINT = "/api/restaurant/**";

    @Autowired
    @Lazy
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers(REGISTER_ENDPOINT).not().fullyAuthenticated()
                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
                .antMatchers(RESTAURANT_ENDPOINT).authenticated()
                .and().httpBasic();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }
}
