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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
                        .requestMatchers("/api/v1/users/signin").permitAll()
                        .anyRequest().authenticated());

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Add Customer filter
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        http.csrf(csrf -> csrf.disable());
        // Handle Exceptions
        http.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler));

        return http.build();

    }

    @Bean
    public CustomUserDetailService userDetailsService() {
        return new CustomUserDetailService();
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
