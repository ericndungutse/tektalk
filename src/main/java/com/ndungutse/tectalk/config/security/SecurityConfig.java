package com.ndungutse.tectalk.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ndungutse.tectalk.config.jwt.JwtEntryPoint;
import com.ndungutse.tectalk.config.jwt.JwtFilterChain;

@Configuration
@EnableWebSecurity
// Allow Method/ Route handler level authorizatoin like @PreAuthorize
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    DataSource dataSource;

    @Autowired
    JwtEntryPoint unauthorizedHandler;

    @Bean
    public JwtFilterChain authenticationJwtTokenFilter() {
        return new JwtFilterChain();
    }

    // Security filter chain custom configration
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                request -> request
                        .requestMatchers("/api/v1/users/signup")
                        .permitAll()
                        .anyRequest().authenticated());

        // Disable CSRF for h2-console
        // http.csrf(csrf -> csrf.disable());
        http.csrf(AbstractHttpConfigurer::disable);

        // Add Customer filter
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        // Handle Exceptions
        http.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler));

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();

    }

    // NOT NEEDED WHEN I HAVE ENTITY
    @Bean
    public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        return jdbcUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }

}
