package rasmus.nisha.codersblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import rasmus.nisha.codersblog.filter.AuthenticationFilter;
import rasmus.nisha.codersblog.services.UserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .sessionManagement(sessConf -> sessConf.sessionCreationPolicy(SessionCreationPolicy.STATELESS))// Spring has built-in session management and takes care of who is logged in etc. if we don't want that to happens rather we want to create JWT tokens to do authentication and authorization. We do this.
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/user/login", "/api/vulnerability/add", "/api/vulnerability/all"))
/*
                .csrf(AbstractHttpConfigurer::disable)
*/
                .authorizeHttpRequests(auth -> auth.requestMatchers("/api/user/login", "/api/blog/all", "/api/blog/{blogId}", "/api/blog/search/{value}" , "/api/vulnerability/add").permitAll()
                        .requestMatchers("/api/blog/delete-all", "/api/vulnerability/all").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .authenticationProvider(authProvider())
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public AuthenticationFilter authenticationFilter() {
        return new AuthenticationFilter();
    }

    @Bean
    public AuthenticationProvider authProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


}
