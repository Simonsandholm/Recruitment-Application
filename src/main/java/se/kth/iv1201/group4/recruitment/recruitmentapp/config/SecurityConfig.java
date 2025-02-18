package se.kth.iv1201.group4.recruitment.recruitmentapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configures Spring security settings including login, logout, and URL access.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures security filters for HTTP requests.
     *
     * @param http The HttpSecurity object.
     * @return A SecurityFilterChain for HTTP security.
     * @throws Exception If there's an error in configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/login", "/user/register").permitAll()
                        .requestMatchers("/person/recruiter").hasAuthority("recruiter")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/person/dashboard", true)
                        .permitAll()
                )
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );
        return http.build();
    }

}
